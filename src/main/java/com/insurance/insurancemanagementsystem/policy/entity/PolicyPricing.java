package com.insurance.insurancemanagementsystem.policy.entity;

import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "policy_pricing")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Policy type must not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type", nullable = false, unique = true)
    private PolicyType policyType;

    @NotNull(message = "Base premium is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Base premium must be greater than zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePremium;

    @Column(nullable = false)
    private boolean active = true;

}

