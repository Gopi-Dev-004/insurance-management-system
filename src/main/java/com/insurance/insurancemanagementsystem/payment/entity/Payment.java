package com.insurance.insurancemanagementsystem.payment.entity;

import com.insurance.insurancemanagementsystem.common.enums.PaymentMethod;
import com.insurance.insurancemanagementsystem.common.enums.PaymentStatus;
import com.insurance.insurancemanagementsystem.policy.entity.Policy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "paymentReference", nullable = false, unique = true)
    private String paymentReference;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status",nullable = false)
    PaymentStatus paymentStatus;

    @Column(name = "Transaction_id")
    private String transactionId;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;



}
