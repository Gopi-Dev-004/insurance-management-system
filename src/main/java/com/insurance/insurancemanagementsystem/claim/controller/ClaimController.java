package com.insurance.insurancemanagementsystem.claim.controller;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimRequestDTO;
import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import com.insurance.insurancemanagementsystem.claim.service.ClaimServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ClaimController {
    private ClaimServiceInterface claimServiceInterface;

    @GetMapping("/viewClaimCustomer")
    public ResponseEntity<List<ClaimResponseDTO>> viewClaimCustomer(@RequestParam Long customer_Id) {
        return claimServiceInterface.viewClaimCustomer(customer_Id);
    }

    @PostMapping(value = "/createClaim", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String > createClaim(@Valid @ModelAttribute  ClaimRequestDTO dto) throws IOException {

        return claimServiceInterface.createClaim(dto);
    }

}
