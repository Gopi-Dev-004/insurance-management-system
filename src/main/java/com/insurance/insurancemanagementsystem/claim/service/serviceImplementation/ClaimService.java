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

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ClaimService implements ClaimServiceInterface {
    private InsuranceRepository insuranceRepository;


    @Override
    public ResponseEntity<List<ClaimResponseDTO>> viewClaimCustomer(Long customer_Id) {
        ClaimResponseDTO dto = new ClaimResponseDTO();
        List<Insurance> insurance1 = insuranceRepository.findByCustomerCustomerId(customer_Id);
        for(Insurance insurance:insurance1 ){
        dto.setId(insurance.getId());
        dto.setName(insurance.getVehicle().getCarDetails().getModel().getName());
        dto.setPolicyStatus(insurance.getPolicy().getPolicyStatus());
        dto.setIdvValue(insurance.getVehicle().getIdvValue());
        dto.setRegistrationNumber(insurance.getVehicle().getRegistrationNumber());
}

        return ResponseEntity.ok(Collections.singletonList(dto));
    }
}
