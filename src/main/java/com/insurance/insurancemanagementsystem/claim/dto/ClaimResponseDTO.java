package com.insurance.insurancemanagementsystem.claim.dto;

import com.insurance.insurancemanagementsystem.common.enums.PolicyStatus;
import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimResponseDTO {
    private Long id;
    private String registrationNumber;
    private String name;
    private PolicyType policyType;
    private PolicyStatus policyStatus;
    private BigDecimal idvValue;

}
