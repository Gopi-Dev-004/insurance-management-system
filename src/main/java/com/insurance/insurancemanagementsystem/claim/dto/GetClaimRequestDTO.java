package com.insurance.insurancemanagementsystem.claim.dto;

import com.insurance.insurancemanagementsystem.common.enums.ClaimStatus;
import com.insurance.insurancemanagementsystem.common.enums.ClaimType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetClaimRequestDTO {

        private ClaimType claimType;
        private ClaimStatus claimStatus;
}
