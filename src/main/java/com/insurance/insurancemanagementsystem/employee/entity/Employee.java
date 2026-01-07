package com.insurance.insurancemanagementsystem.employee.entity;

import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String name;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private LocalDate doj;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialization", nullable = false)
    private ClaimSpecialization specialization;

    @Column(name = "active_claim_count")
    private Integer activeClaimCount = 0;

}

