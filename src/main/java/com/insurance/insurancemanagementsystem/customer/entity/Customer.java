package com.insurance.insurancemanagementsystem.customer.entity;



import com.insurance.insurancemanagementsystem.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @OneToOne
    @JoinColumn( name = "user_id", nullable = false)
    private User user;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private String Gender;

    private String email;

    private String mobile;

    private String address;

    private String KycStatus;  // PENDING, VERIFIED, REJECTED



}
