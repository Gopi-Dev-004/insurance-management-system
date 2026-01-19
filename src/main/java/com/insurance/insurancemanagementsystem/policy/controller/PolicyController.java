package com.insurance.insurancemanagementsystem.policy.controller;

import com.insurance.insurancemanagementsystem.insurance.dto.PolicyPaymentRequestDTO;
import com.insurance.insurancemanagementsystem.policy.dto.PolicyPremiumCalculationResponseDTO;
import com.insurance.insurancemanagementsystem.policy.dto.ThirdPartyQuoteResponseDTO;
import com.insurance.insurancemanagementsystem.policy.service.PolicyServiceInterface;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/policy")
public class PolicyController {

    public final PolicyServiceInterface policyService;




    @GetMapping("/payableAmout")
    public ResponseEntity<ThirdPartyQuoteResponseDTO> getPayableAmount(@RequestParam Long customerId, @Valid @RequestBody PolicyPremiumCalculationResponseDTO dto){

        return ResponseEntity.ok(policyService.getPayableAmount(customerId,dto));

    }
    @PostMapping("/pay")
    public ResponseEntity<String> createPayment(@RequestBody PolicyPaymentRequestDTO policyPaymentRequestDTO) throws MessagingException {
        return policyService.createPayment(policyPaymentRequestDTO);
    }

}
