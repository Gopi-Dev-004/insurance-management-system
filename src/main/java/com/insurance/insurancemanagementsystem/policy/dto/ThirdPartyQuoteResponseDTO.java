package com.insurance.insurancemanagementsystem.policy.dto;

import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyQuoteResponseDTO {

    private BigDecimal basePremium;

    private BigDecimal totalPremiumAmount; // Base + GST

    private PolicyType policyType;

    private Integer policyDurationInYears;

    private LocalDate coverageStartDate;

    private LocalDate coverageEndDate;

}
