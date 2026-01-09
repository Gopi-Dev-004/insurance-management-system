package com.insurance.insurancemanagementsystem.admin.controller;

import com.insurance.insurancemanagementsystem.admin.service.AdminServiceInterface;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
@AllArgsConstructor
public class AdminController {
  private AdminServiceInterface adminServiceInterface;
  @PostMapping("/signinEmployee")
    public ResponseEntity<String> AddEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){

      return adminServiceInterface.AddEmployee(employeeRequestDTO);
  }

}
