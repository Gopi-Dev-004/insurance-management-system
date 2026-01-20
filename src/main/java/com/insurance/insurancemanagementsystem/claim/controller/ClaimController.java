package com.insurance.insurancemanagementsystem.claim.controller;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import com.insurance.insurancemanagementsystem.claim.service.ClaimServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClaimController {
   private ClaimServiceInterface claimServiceInterface;
    @GetMapping("/viewClaimCustomer")
public ResponseEntity<List<ClaimResponseDTO>>viewClaimCustomer(@RequestParam Long customer_Id){
        return claimServiceInterface.viewClaimCustomer(customer_Id);
    }
}
