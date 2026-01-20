package com.insurance.insurancemanagementsystem.claim.service;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClaimServiceInterface {
     ResponseEntity<List<ClaimResponseDTO>> viewClaimCustomer(Long customer_Id);
}
