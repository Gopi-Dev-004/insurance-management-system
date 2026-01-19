package com.insurance.insurancemanagementsystem.insurance.dto;

import com.insurance.insurancemanagementsystem.common.enums.ClaimStatus;
import com.insurance.insurancemanagementsystem.common.enums.InsuranceStatus;
import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import com.insurance.insurancemanagementsystem.payment.entity.Payment;
import com.insurance.insurancemanagementsystem.policy.entity.Policy;
import com.insurance.insurancemanagementsystem.vehicle.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceRequestDTO {

    private InsuranceStatus insuranceStatus;
    private ClaimStatus claimStatus;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private LocalDateTime createdAt;
}
