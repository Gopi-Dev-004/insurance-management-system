package com.insurance.insurancemanagementsystem.admin.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.admin.service.AdminServiceInterface;
import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeResponseDTO;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import com.insurance.insurancemanagementsystem.employee.repository.EmployeeRepository;
import com.insurance.insurancemanagementsystem.user.Repository.UserRepository;
import com.insurance.insurancemanagementsystem.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService implements AdminServiceInterface {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    @Override
    public ResponseEntity<String> AddEmployee(ClaimSpecialization specialization,EmployeeRequestDTO employeeRequestDTO) {
         Employee employee = new Employee();
         employee.setName(employeeRequestDTO.getName());
         employee.setGender(employeeRequestDTO.getGender());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setSpecialization(specialization);
       User user= userRepository.findByUsername(employeeRequestDTO.getName()).
        orElseThrow(()->new RuntimeException("Not valid name"));
       employee.setUser(user);
        employeeRepository.save(employee);
        return ResponseEntity.ok("successfully");
    }

    @Override
    public ResponseEntity<Page<Employee>> AllEmployeeDetail(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber,5);
      Page<Employee> employees=employeeRepository.findAll(pageable);
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<Page<Employee>> FindByEmployees(ClaimSpecialization specialization,int pageNumber) {
        Pageable pageable=PageRequest.of(pageNumber,5);
        Page<Employee>employees=employeeRepository.findBySpecialization(specialization,pageable);
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> UpdateEmployee(Long id,EmployeeRequestDTO employeeRequestDTO) {
      Employee employee =employeeRepository.findById(id).
                orElseThrow(()->new RuntimeException("Not valid"));
      employee.setName(employeeRequestDTO.getName());
      employee.setEmail(employeeRequestDTO.getEmail());
      employee.setGender(employeeRequestDTO.getGender());
      employee.setAddress(employeeRequestDTO.getAddress());
      employee.setPhone(employeeRequestDTO.getPhone());
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeResponseDTO responseDTO = mapToResponseDTO(savedEmployee);
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    public ResponseEntity<String> DeleteEmployee(Long id) {
   Employee employee= employeeRepository.findById(id).
           orElseThrow(()->new RuntimeException("Not valid"));
         User user= employee.getUser();
        employeeRepository.delete(employee);
        if (user != null) {
            userRepository.delete(user);
        }

        return ResponseEntity.ok("delete successfully");
    }

    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setGender(employee.getGender());
        dto.setAddress(employee.getAddress());
        dto.setPhone(employee.getPhone());
        return dto;
    }

}

