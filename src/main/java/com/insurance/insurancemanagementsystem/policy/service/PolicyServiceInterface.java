package com.insurance.insurancemanagementsystem.policy.service;

import com.insurance.insurancemanagementsystem.policy.dto.PolicyPremiumCalculationResponseDTO;

import java.math.BigDecimal;

public interface PolicyServiceInterface {

    BigDecimal getPayableAmount(PolicyPremiumCalculationResponseDTO dto);

}
