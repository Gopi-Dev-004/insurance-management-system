package com.insurance.insurancemanagementsystem.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_kyc")
public class CustomerKyc {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long kycId;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false,unique = true)
    private Customer customer;

    private String aadhar;
    private String pan;
    private LocalDateTime verifiedAt;

}
