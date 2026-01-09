package com.insurance.insurancemanagementsystem.admin.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.admin.service.AdminServiceInterface;
import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import com.insurance.insurancemanagementsystem.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService implements AdminServiceInterface {

    private EmployeeRepository employeeRepository;
    @Override
    public ResponseEntity<String> AddEmployee(EmployeeRequestDTO employeeRequestDTO) {
         Employee employee = new Employee();
         employee.setName(employeeRequestDTO.getName());
         employee.setGender(employeeRequestDTO.getGender());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setSpecialization(ClaimSpecialization.ACCIDENTAL_DAMAGE);
        employeeRepository.save(employee);
        return ResponseEntity.ok("successfully");
    }
}
