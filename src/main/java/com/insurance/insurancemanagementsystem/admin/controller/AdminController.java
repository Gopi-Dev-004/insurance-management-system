package com.insurance.insurancemanagementsystem.admin.controller;
import com.insurance.insurancemanagementsystem.admin.service.AdminServiceInterface;
import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.customer.dto.CustomerRequestDTO;
import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeResponseDTO;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import com.insurance.insurancemanagementsystem.employee.repository.EmployeeRepository;
import com.insurance.insurancemanagementsystem.insurance.dto.InsuranceRequestDTO;
import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import com.insurance.insurancemanagementsystem.vehicle.dto.*;
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
@PutMapping("/updateCarDetails")
    public  ResponseEntity<String> UpdateDetailsCar(@Valid @RequestBody CarDetailsUpdateRequestDTO carDetailsRequestDTO){

      return adminServiceInterface.CarDeatilsUpdate(carDetailsRequestDTO);

}
@PutMapping("/updateCarModel")
    public ResponseEntity<String> UpdateCarModel(@RequestBody CarModelUpdateRequestDTO carModelUpdateRequestDTO){
      return adminServiceInterface.UpdateCarModel(carModelUpdateRequestDTO);
}
@PutMapping("/updateManufacturer")
    public ResponseEntity<String> UpdateManufacturer(@RequestBody UpdateManufacturerDTO updateManufacturerDTO){

      return adminServiceInterface.UpdateManufacturer(updateManufacturerDTO);
}@PutMapping("/vehicleUpdate")
public ResponseEntity<String > AllUpdateCar(@RequestBody VehicleUpdateRequestDTO vehicleUpdateRequestDTO){
      return adminServiceInterface.VehicleUpdate(vehicleUpdateRequestDTO);
    }
@PostMapping("/saveUser")
public ResponseEntity<String> SaveUser(@RequestParam Long id, @RequestBody CustomerRequestDTO customerRequestDTO){
      return adminServiceInterface.AddUserDetails(id, customerRequestDTO);
}
@GetMapping("/viewAllCustomer")
    public ResponseEntity<Page<Customer>>AllCustomer(@RequestParam int pageNumber){
      return adminServiceInterface.AllCustomerDetails(pageNumber);
}
@GetMapping("/ViewCoustomer")
    public ResponseEntity<Customer> ViewCoustomer(@RequestParam String mobile){
      return adminServiceInterface.ViewCustomer(mobile);
}
@PutMapping("/updateCustomer")
    public ResponseEntity<String>UpdateCustomer(  @RequestParam Long id,@RequestBody CustomerRequestDTO customerRequestDTO){
      return adminServiceInterface.UpdateCustomer(id,customerRequestDTO);
}
@DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> DeleteCustomer( @RequestParam Long id){
      return adminServiceInterface.DeleteCustomer(id);
}
@GetMapping("/viewAllInsurance")
    public ResponseEntity<Page<Insurance>> ViewAllInsurance(@RequestParam int pageNumber){
      return adminServiceInterface.viewAllInsurance(pageNumber);
}
@PutMapping("/updateInsurance")
  public  ResponseEntity<String> UpdateInsurance(@RequestParam Long id,@RequestBody InsuranceRequestDTO insuranceRequestDTO){
      return adminServiceInterface.UpdateInsurance(id,insuranceRequestDTO);
}

}
