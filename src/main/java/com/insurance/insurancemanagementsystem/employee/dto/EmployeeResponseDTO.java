package com.insurance.insurancemanagementsystem.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String address;


}
