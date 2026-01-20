package com.insurance.insurancemanagementsystem.policy.dto;

import com.insurance.insurancemanagementsystem.common.enums.PolicyDuration;
import com.insurance.insurancemanagementsystem.policy.entity.Addon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyAddonRequestDTO {
    private PolicyDuration addonDuration;
    private BigDecimal addonPremium;
}
