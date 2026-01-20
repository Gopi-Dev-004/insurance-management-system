package com.insurance.insurancemanagementsystem.claim.service;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ClaimServiceInterface {
    ResponseEntity<ClaimResponseDTO> viewClaimCustomer(Long userId);
}
