package com.insurance.insurancemanagementsystem.admin.controller;
import com.insurance.insurancemanagementsystem.admin.service.AdminServiceInterface;
import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeResponseDTO;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import com.insurance.insurancemanagementsystem.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/admin")
@AllArgsConstructor
public class AdminController {
  private AdminServiceInterface adminServiceInterface;
  @PostMapping("/signInEmployee")
    public ResponseEntity<String> AddEmployee(@RequestParam ClaimSpecialization specialization,@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){

      return adminServiceInterface.AddEmployee(specialization,employeeRequestDTO);
  }
@GetMapping("/allEmployee")
    public ResponseEntity<Page<Employee>> AllEmployeeDetails(@RequestParam int pageNumber){

      return adminServiceInterface.AllEmployeeDetail(pageNumber);
}
@GetMapping("/findEmployee")
    public ResponseEntity<Page<Employee>> FindByEmployee(@RequestParam ClaimSpecialization specialization, @RequestParam int pageNumber){
      return adminServiceInterface.FindByEmployees(specialization,pageNumber);
}
@PutMapping("/updataEmployee")
    public ResponseEntity<EmployeeResponseDTO> UpdateEmployee(@RequestParam Long id,@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){

      return adminServiceInterface.UpdateEmployee(id,employeeRequestDTO);
}
@DeleteMapping("/deleteEmployee")
    public  ResponseEntity<String> DeleteEmployee(@RequestParam Long id){

      return adminServiceInterface.DeleteEmployee(id);
}

}
