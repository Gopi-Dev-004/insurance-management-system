package com.insurance.insurancemanagementsystem.admin.service;

import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeResponseDTO;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AdminServiceInterface {
    ResponseEntity<String> AddEmployee(ClaimSpecialization specialization,EmployeeRequestDTO employeeRequestDTO);
    ResponseEntity<Page<Employee>> AllEmployeeDetail(int pageNumber);
    ResponseEntity<Page<Employee>> FindByEmployees(ClaimSpecialization specialization, int pageNumber);
    ResponseEntity<EmployeeResponseDTO>UpdateEmployee(Long id,EmployeeRequestDTO employeeRequestDTO);
   ResponseEntity<String> DeleteEmployee(Long id);
}
