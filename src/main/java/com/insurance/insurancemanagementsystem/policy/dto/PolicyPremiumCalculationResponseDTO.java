package com.insurance.insurancemanagementsystem.policy.dto;

import com.insurance.insurancemanagementsystem.common.enums.AddonType;
import com.insurance.insurancemanagementsystem.common.enums.PolicyDuration;
import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyPremiumCalculationResponseDTO {

    @NotNull(message = "Car ID is required")
    private Long carId;

    @NotNull(message = "Vehicle age is required")
    @PastOrPresent
    private LocalDate vehicleRegistrationDate;

    @NotNull(message = "Policy type is required")
    private PolicyType policyType;

    private List<AddonType> addonTypes;

    @NotNull(message = "Policy duration is mandatory")
    private PolicyDuration policyDuration;
}
