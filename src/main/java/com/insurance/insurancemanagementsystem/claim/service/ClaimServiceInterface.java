package com.insurance.insurancemanagementsystem.claim.service;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimRequestDTO;
import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ClaimServiceInterface {
     ResponseEntity<List<ClaimResponseDTO>> viewClaimCustomer(Long customer_Id);

     ResponseEntity<String> createClaim(@Valid ClaimRequestDTO dto) throws IOException;
}
