package com.insurance.insurancemanagementsystem.insurance.dto;

import com.insurance.insurancemanagementsystem.common.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyPaymentRequestDTO {
    private Long customerId;
    private Long policyId;
    private PaymentMethod paymentMethod;

}
