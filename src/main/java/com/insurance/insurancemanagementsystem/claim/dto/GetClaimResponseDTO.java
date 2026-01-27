package com.insurance.insurancemanagementsystem.claim.dto;

import com.insurance.insurancemanagementsystem.common.enums.ClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetClaimResponseDTO {


    private Long insuranceId;

    private LocalDateTime accidentDateTime;

    private String accidentLocation;

    private String description;

    private ClaimStatus claimStatus;

    private BigDecimal claimAmount;

    private LocalDateTime submittedAt;
}
