package com.insurance.insurancemanagementsystem.claim.service;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimRequestDTO;
import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import com.insurance.insurancemanagementsystem.claim.dto.GetClaimRequestDTO;
import com.insurance.insurancemanagementsystem.claim.dto.GetClaimResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ClaimServiceInterface {
    ResponseEntity<List<ClaimResponseDTO>> viewClaimCustomer(Long customer_Id);

    ResponseEntity<String> createClaim(@Valid ClaimRequestDTO dto) throws IOException;

    Page<GetClaimResponseDTO> getClaimByTypeAndStatus(GetClaimRequestDTO dto, int pageNumber);
}
