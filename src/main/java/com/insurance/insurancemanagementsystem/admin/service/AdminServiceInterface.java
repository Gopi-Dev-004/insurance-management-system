package com.insurance.insurancemanagementsystem.admin.service;

import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AdminServiceInterface {
    ResponseEntity<String> AddEmployee(EmployeeRequestDTO employeeRequestDTO);
}
