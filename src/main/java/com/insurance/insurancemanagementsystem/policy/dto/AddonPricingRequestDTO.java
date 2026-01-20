package com.insurance.insurancemanagementsystem.policy.dto;

import com.insurance.insurancemanagementsystem.common.enums.AddonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddonPricingRequestDTO {
    private AddonType addonType;
    private BigDecimal price;
    private boolean active;
}
