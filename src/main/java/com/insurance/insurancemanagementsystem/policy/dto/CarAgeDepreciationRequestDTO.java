package com.insurance.insurancemanagementsystem.policy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarAgeDepreciationRequestDTO {
    private Integer age;
    private BigDecimal depreciationPercentage;
    private boolean active;
}
