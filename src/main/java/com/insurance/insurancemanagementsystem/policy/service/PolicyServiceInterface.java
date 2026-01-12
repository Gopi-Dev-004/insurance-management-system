package com.insurance.insurancemanagementsystem.policy.service;

import com.insurance.insurancemanagementsystem.policy.dto.PolicyPremiumCalculationResponseDTO;
import com.insurance.insurancemanagementsystem.policy.dto.ThirdPartyQuoteResponseDTO;

import java.math.BigDecimal;

public interface PolicyServiceInterface {

    ThirdPartyQuoteResponseDTO getPayableAmount(PolicyPremiumCalculationResponseDTO dto);

}
