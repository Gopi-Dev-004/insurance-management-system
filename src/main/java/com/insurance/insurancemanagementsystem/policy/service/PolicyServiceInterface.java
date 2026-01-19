package com.insurance.insurancemanagementsystem.policy.service;

import com.insurance.insurancemanagementsystem.insurance.dto.PolicyPaymentRequestDTO;
import com.insurance.insurancemanagementsystem.policy.dto.PolicyPremiumCalculationResponseDTO;
import com.insurance.insurancemanagementsystem.policy.dto.ThirdPartyQuoteResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface PolicyServiceInterface {

    ThirdPartyQuoteResponseDTO getPayableAmount(Long customerId,PolicyPremiumCalculationResponseDTO dto);

    ResponseEntity<String> createPayment(PolicyPaymentRequestDTO policyPaymentRequestDTO) throws MessagingException;
}
