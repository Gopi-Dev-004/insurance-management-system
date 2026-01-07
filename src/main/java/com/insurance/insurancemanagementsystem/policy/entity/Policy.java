package com.insurance.insurancemanagementsystem.policy.entity;

import com.insurance.insurancemanagementsystem.common.enums.PolicyStatus;
import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import com.insurance.insurancemanagementsystem.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.descriptor.java.IntegerPrimitiveArrayJavaType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "policy")
public class Policy {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_number", nullable = false, unique = true)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type", nullable = false)
    private PolicyType policyType;

    @OneToOne
    @JoinColumn(name ="vehicle_id")
    private Vehicle vehicle;

    @Column(name = "base_premium", nullable = false)
    private BigDecimal basePremium;

    @Column(name = "total_premium", nullable = false)
    private BigDecimal totalPremium;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_status", nullable = false)
    private PolicyStatus policyStatus;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private List<PolicyAddon> addons;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
