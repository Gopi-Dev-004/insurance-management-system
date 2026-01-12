package com.insurance.insurancemanagementsystem.user.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false,unique = true)
    private String roleName;  // ROLE_CUSTOMER, ROLE_EMPLOYEE, ROLE_ADMIN
}
