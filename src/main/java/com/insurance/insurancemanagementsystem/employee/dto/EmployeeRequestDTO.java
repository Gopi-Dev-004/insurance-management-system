package com.insurance.insurancemanagementsystem.employee.dto;

import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
  @NotBlank(message = "Not null")
    private String name;
    private String gender;
    @Email(message = "only email type")
    private String email;
    private String phone;
    private String address;
}
