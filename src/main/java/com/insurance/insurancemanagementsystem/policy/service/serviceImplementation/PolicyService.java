package com.insurance.insurancemanagementsystem.policy.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import com.insurance.insurancemanagementsystem.common.exception.ResourceNotFoundException;
import com.insurance.insurancemanagementsystem.common.util.PolicyUtil;
import com.insurance.insurancemanagementsystem.policy.dto.PolicyPremiumCalculationResponseDTO;
import com.insurance.insurancemanagementsystem.policy.entity.AddonPricing;
import com.insurance.insurancemanagementsystem.policy.entity.PolicyPricing;
import com.insurance.insurancemanagementsystem.policy.repository.AddonPricingRepository;
import com.insurance.insurancemanagementsystem.policy.repository.PolicyPricingRepository;
import com.insurance.insurancemanagementsystem.policy.service.PolicyServiceInterface;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PolicyService implements PolicyServiceInterface {

    CarDetailsRepository carDetailsRep;

//    CarAgeDepreciationRepository depreciationRep;

    PolicyPricingRepository policyPricingRep;

    AddonPricingRepository addonPricingRep;

    @Override
    public BigDecimal getPayableAmount(PolicyPremiumCalculationResponseDTO dto) {


        // for THIRD_PARTY policy

        CarDetails carDetails = carDetailsRep.findById(dto.getCarId())
                .orElseThrow(() -> new ResourceNotFoundException("Car not found...!"));

        if (dto.getPolicyDuration() == null)
            throw new IllegalArgumentException("Policy duration is mandatory");

        if (dto.getPolicyType() == null)
            throw new IllegalArgumentException("Policy type is mandatory ");

        // getting policy details for get basic premium
        PolicyPricing policyPricing = policyPricingRep.findByPolicyType(dto.getPolicyType());

        BigDecimal finalPayableAmount = BigDecimal.ZERO;

        if (dto.getPolicyType() == PolicyType.THIRD_PARTY)
        {

            BigDecimal yearlyPremium = policyPricing.getBasePremium();

            finalPayableAmount =
                    PolicyUtil.calculateFinalPremium(
                            dto.getPolicyDuration(),
                            yearlyPremium
                    );

            return finalPayableAmount;
        }

        //  for OWN_DAMAGE, COMPREHENSIVE policy

/*
        int carAge = (int) ChronoUnit.YEARS.between(dto.getVehicleRegistrationDate(), LocalDate.now());

        CarAgeDepreciation depreciation;

        if (carAge <= 5) {
            depreciation = depreciationRep.findByAge(carAge);
        } else {
            depreciation = depreciationRep.findByAge(10);
        }

         find idv value

        BigDecimal idv = carDetails
                .getIdvValue().subtract(carDetails
                        .getIdvValue().
                        multiply(depreciation.getDepreciationPercentage()
                                .divide(BigDecimal.valueOf(100))));



        BigDecimal basePremium = idv
                .multiply(policyPricing.getBasePremium())
                .divide(BigDecimal.valueOf(100));
*/

        // addon
        BigDecimal addonPrice = BigDecimal.ZERO;

        if (dto.getPolicyType() == PolicyType.COMPREHENSIVE)
        {
            addonPrice = addonPricingRep.findByActiveTrue()
                    .stream()
                    .map(AddonPricing::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        else if (dto.getPolicyType() == PolicyType.OWN_DAMAGE && dto.getAddonTypes()
                != null && !dto.getAddonTypes().isEmpty())
        {

            addonPrice = dto.getAddonTypes().stream()
                    .map(type -> addonPricingRep.findByAddonType(type))
                    .map(AddonPricing::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        else
        {
            throw new IllegalArgumentException("Invalid policy type");
        }

        finalPayableAmount = policyPricing.getBasePremium().add(addonPrice);

        return finalPayableAmount;

    }
}