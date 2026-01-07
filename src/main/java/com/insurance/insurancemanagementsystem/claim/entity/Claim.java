package com.insurance.insurancemanagementsystem.claim.entity;

import com.insurance.insurancemanagementsystem.common.enums.ClaimStatus;
import com.insurance.insurancemanagementsystem.common.enums.ClaimType;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
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
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id", nullable = false)
    private Insurance insurance;

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_type", nullable = false)
    private ClaimType claimType;

    @Column(name = "accident_datetime", nullable = false)
    private LocalDateTime accidentDateTime;

    @Column(name = "accident_location", nullable = false)
    private String accidentLocation;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_status", nullable = false)
    private ClaimStatus claimStatus;

    @Column(name = "claimed_amount")
    private BigDecimal claimedAmount;

    @Column(name = "approved_amount")
    private BigDecimal approvedAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id")
    private Employee assignedEmployee;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.claimStatus = ClaimStatus.CLAIM_REQUESTED;
    }
}

