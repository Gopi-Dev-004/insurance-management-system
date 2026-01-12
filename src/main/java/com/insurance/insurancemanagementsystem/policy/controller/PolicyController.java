package com.insurance.insurancemanagementsystem.policy.controller;

import com.insurance.insurancemanagementsystem.policy.dto.PolicyPremiumCalculationResponseDTO;
import com.insurance.insurancemanagementsystem.policy.dto.ThirdPartyQuoteResponseDTO;
import com.insurance.insurancemanagementsystem.policy.service.PolicyServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/policy")
public class PolicyController {

    public final PolicyServiceInterface policyService;

    @GetMapping("/payableAmout")
    public ResponseEntity<ThirdPartyQuoteResponseDTO> getPayableAmount(@Valid @RequestBody PolicyPremiumCalculationResponseDTO dto){

        return ResponseEntity.ok(policyService.getPayableAmount(dto));

    }

}
