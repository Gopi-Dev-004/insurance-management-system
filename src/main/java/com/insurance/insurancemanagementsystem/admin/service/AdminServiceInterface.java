package com.insurance.insurancemanagementsystem.admin.service;

import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.customer.dto.CustomerRequestDTO;
import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeResponseDTO;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import com.insurance.insurancemanagementsystem.insurance.dto.InsuranceRequestDTO;
import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import com.insurance.insurancemanagementsystem.policy.dto.*;
import com.insurance.insurancemanagementsystem.policy.entity.*;
import com.insurance.insurancemanagementsystem.vehicle.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AdminServiceInterface {
    ResponseEntity<String> AddEmployee(ClaimSpecialization specialization, EmployeeRequestDTO employeeRequestDTO);

    ResponseEntity<Page<Employee>> AllEmployeeDetail(int pageNumber);

    ResponseEntity<Page<Employee>> FindByEmployees(ClaimSpecialization specialization, int pageNumber);

    ResponseEntity<EmployeeResponseDTO> UpdateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO);

    ResponseEntity<String> DeleteEmployee(Long id);

    ResponseEntity<String> CarDeatilsUpdate(CarDetailsUpdateRequestDTO carDetailsUpdateRequestDTO);

    ResponseEntity<String> UpdateCarModel(CarModelUpdateRequestDTO carModelUpdateRequestDTO);

    ResponseEntity<String> UpdateManufacturer(UpdateManufacturerDTO updateManufacturerDTO);

    ResponseEntity<String> VehicleUpdate(VehicleUpdateRequestDTO vehicleUpdateRequestDTO);

    ResponseEntity<String> AddUserDetails(Long id, CustomerRequestDTO customerRequestDTO);

    ResponseEntity<Page<Customer>> AllCustomerDetails(int pageNumber);

    ResponseEntity<Customer> ViewCustomer(String mobile);

    ResponseEntity<String> UpdateCustomer(Long id, CustomerRequestDTO customerRequestDTO);

    ResponseEntity<String> DeleteCustomer(Long id);

    ResponseEntity<Page<Insurance>> viewAllInsurance(int pageNumber);

    ResponseEntity<String> UpdateInsurance(Long id, InsuranceRequestDTO insuranceRequestDTO);

    ResponseEntity<Page<Addon>> addonAllDetails(int pageNumber);

    ResponseEntity<String> updateAddon(Long addonId, AddonRequestDTO addonRequestDTO);

    ResponseEntity<Page<AddonPricing>> viewAddonPricing(int pageNumber);

    ResponseEntity<String> updateAddonPricing(Long addonpicingId, AddonPricingRequestDTO addonPricingRequestDTO);

    ResponseEntity<Page<CarAgeDepreciation>> viewCarAgeDepreciation(int pageNumber);

    ResponseEntity<String> updateCarAgeDepreciation(Long carageId, CarAgeDepreciationRequestDTO carAgeDepreciationRequestDTO);

    ResponseEntity<Page<PolicyAddon>> viewPolicyAddon(int pageNumber);

    ResponseEntity<String> updatePolicyAddon(Long policyId, PolicyAddonRequestDTO addonRequestDTO);

    ResponseEntity<Page<PolicyPricing>> viewPolicyPricing(int pageNumber);

    ResponseEntity<String> updatePolicyPricing(Long id, PolicyPricingRequestDTO dto);
}
