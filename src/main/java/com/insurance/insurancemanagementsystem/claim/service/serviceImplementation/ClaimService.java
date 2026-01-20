package com.insurance.insurancemanagementsystem.claim.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import com.insurance.insurancemanagementsystem.claim.service.ClaimServiceInterface;
import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import com.insurance.insurancemanagementsystem.customer.repository.CustomerRepository;
import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import com.insurance.insurancemanagementsystem.insurance.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClaimService implements ClaimServiceInterface {
    private InsuranceRepository insuranceRepository;
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<ClaimResponseDTO> viewClaimCustomer(Long userId) {
        ClaimResponseDTO dto = new ClaimResponseDTO();
        Insurance insurance = insuranceRepository.findByCustomer_Id(userId);
        dto.setId(insurance.getId());
        dto.setName(insurance.getVehicle().getCarDetails().getModel().getName());
        dto.setPolicyStatus(insurance.getPolicy().getPolicyStatus());
        dto.setIdvValue(insurance.getVehicle().getIdvValue());
        dto.setRegistrationNumber(insurance.getVehicle().getRegistrationNumber());


        return ResponseEntity.ok(dto);
    }
}
