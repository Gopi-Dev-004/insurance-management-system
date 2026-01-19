package com.insurance.insurancemanagementsystem.customer.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    private String firstName;

    private String lastName;

    private LocalDate dob;

    private String Gender;

    private String email;

    private String mobile;

    private String address;

    private String KycStatus;
}
