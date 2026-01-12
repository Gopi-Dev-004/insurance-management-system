package com.insurance.insurancemanagementsystem.policy.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.common.enums.AddonType;
import com.insurance.insurancemanagementsystem.common.enums.PolicyDuration;
import com.insurance.insurancemanagementsystem.common.enums.PolicyStatus;
import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import com.insurance.insurancemanagementsystem.common.exception.ResourceNotFoundException;
import com.insurance.insurancemanagementsystem.common.util.PolicyUtil;
import com.insurance.insurancemanagementsystem.policy.dto.PolicyPremiumCalculationResponseDTO;
import com.insurance.insurancemanagementsystem.policy.dto.ThirdPartyQuoteResponseDTO;
import com.insurance.insurancemanagementsystem.policy.entity.*;
import com.insurance.insurancemanagementsystem.policy.repository.AddonPricingRepository;
import com.insurance.insurancemanagementsystem.policy.repository.CarAgeDepreciationRepository;
import com.insurance.insurancemanagementsystem.policy.repository.PolicyPricingRepository;
import com.insurance.insurancemanagementsystem.policy.repository.PolicyRepository;
import com.insurance.insurancemanagementsystem.policy.service.PolicyServiceInterface;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import com.insurance.insurancemanagementsystem.vehicle.entity.Vehicle;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarDetailsRepository;
import com.insurance.insurancemanagementsystem.vehicle.service.serviceImplementation.CarCRUDService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PolicyService implements PolicyServiceInterface {

    CarDetailsRepository carDetailsRep;

    CarAgeDepreciationRepository depreciationRep;

    PolicyPricingRepository policyPricingRep;

    AddonPricingRepository addonPricingRep;

    PolicyRepository policyRepository;

    CarCRUDService carCRUDService;

    @Override
    public ThirdPartyQuoteResponseDTO getPayableAmount(PolicyPremiumCalculationResponseDTO dto) {

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

         Policy policy = createPolicy(dto,policyPricing.getBasePremium(),finalPayableAmount,dto.getPolicyDuration()==PolicyDuration.ONE_YEAR?1:3,BigDecimal.ZERO);

            ThirdPartyQuoteResponseDTO responseDTO = new ThirdPartyQuoteResponseDTO();
            responseDTO.setBasePremium(finalPayableAmount);
            responseDTO.setTotalPremiumAmount(finalPayableAmount);
            responseDTO.setPolicyType(dto.getPolicyType());
            responseDTO.setPolicyDurationInYears(dto.getPolicyDuration()==PolicyDuration.ONE_YEAR?1:3);
            responseDTO.setCoverageStartDate(policy.getStartDate());
            responseDTO.setCoverageEndDate(policy.getEndDate());

            return responseDTO;
        }

        //  for OWN_DAMAGE, COMPREHENSIVE policy

        CarAgeDepreciation depreciation = depreciationRep.findByAge(
                PolicyUtil.normalizeDepreciationAge(
                        PolicyUtil.calculateCarAge(
                                dto.getVehicleRegistrationDate()
                        )
                )
        );

      //   find idv value

        BigDecimal idv = carDetails
                .getIdvValue().subtract(carDetails
                        .getIdvValue().
                        multiply(depreciation.getDepreciationPercentage()
                                .divide(BigDecimal.valueOf(100))));


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

        finalPayableAmount = PolicyUtil.calculateFinalPremium(
                dto.getPolicyDuration(),
                policyPricing.getBasePremium().add(addonPrice)
        );

      Policy policy = createPolicy(dto,policyPricing.getBasePremium(),finalPayableAmount,dto.getPolicyDuration()==PolicyDuration.ONE_YEAR?1:3,idv);

        ThirdPartyQuoteResponseDTO responseDTO = new ThirdPartyQuoteResponseDTO();
        responseDTO.setBasePremium(policyPricing.getBasePremium());
        responseDTO.setTotalPremiumAmount(finalPayableAmount);
        responseDTO.setPolicyType(dto.getPolicyType());
        responseDTO.setPolicyDurationInYears(dto.getPolicyDuration()==PolicyDuration.ONE_YEAR?1:3);
        responseDTO.setCoverageStartDate(policy.getStartDate());
        responseDTO.setCoverageEndDate(policy.getEndDate());
        return responseDTO;
    }


    public Policy createPolicy(PolicyPremiumCalculationResponseDTO dto,BigDecimal basePremium, BigDecimal totalPremium,int yearOfPolicy,BigDecimal idv)
    {
        Policy policy = new Policy();

        //create Vehicle

        CarDetails carDetails = carDetailsRep.findById(dto.getCarId())
                .orElseThrow(() -> new ResourceNotFoundException("Car not found...!"));
        String carRegisterNumber =  dto.getRegistrationNumber();
        LocalDate vehicleRegistrationDate = dto.getVehicleRegistrationDate();
        int carAge =  PolicyUtil.calculateCarAge(dto.getVehicleRegistrationDate());

        Vehicle vehicle = carCRUDService.saveVehicle(carDetails,carRegisterNumber,vehicleRegistrationDate,carAge,idv);

        policy.setPolicyNumber(generatePolicyNumber());

        policy.setPolicyType(dto.getPolicyType());

        policy.setVehicle(vehicle);

        policy.setBasePremium(basePremium);

        policy.setTotalPremium(totalPremium);

        policy.setStartDate(LocalDate.now());

        policy.setEndDate(policy.getStartDate().plusYears(yearOfPolicy));

        policy.setPolicyStatus(PolicyStatus.PENDING_PAYMENT);

        Policy newPolicy = policyRepository.save(policy);

        savePolicyAddon(newPolicy,dto);

        return newPolicy;

    }

    private void savePolicyAddon(Policy newPolicy,PolicyPremiumCalculationResponseDTO dto) {

        if(dto.getAddonTypes() != null && !dto.getAddonTypes().isEmpty()){

             for(AddonType type : dto.getAddonTypes() ){
                 Addon addon = new Addon();

                 addon.setAddonType(type);
                 addon.setActive(true);

                 PolicyAddon policyAddon = new PolicyAddon();

                 policyAddon.setAddon(addon);
                 policyAddon.setPolicy(newPolicy);
                 policyAddon.setAddonDuration(dto.getPolicyDuration());
                 policyAddon.setAddonPremium(addonPricingRep.findByAddonType(type).getPrice());
             }
        }
    }

    private String generatePolicyNumber() {
        return String.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits())).substring(0, 10);
    }
}