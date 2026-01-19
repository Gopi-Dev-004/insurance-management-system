package com.insurance.insurancemanagementsystem.admin.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.admin.service.AdminServiceInterface;
import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.customer.dto.CustomerRequestDTO;
import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import com.insurance.insurancemanagementsystem.customer.repository.CustomerRepository;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeRequestDTO;
import com.insurance.insurancemanagementsystem.employee.dto.EmployeeResponseDTO;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import com.insurance.insurancemanagementsystem.employee.repository.EmployeeRepository;
import com.insurance.insurancemanagementsystem.insurance.dto.InsuranceRequestDTO;
import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import com.insurance.insurancemanagementsystem.insurance.repository.InsuranceRepository;
import com.insurance.insurancemanagementsystem.user.Repository.RoleRepository;
import com.insurance.insurancemanagementsystem.user.Repository.UserRepository;
import com.insurance.insurancemanagementsystem.user.entity.Role;
import com.insurance.insurancemanagementsystem.user.entity.User;
import com.insurance.insurancemanagementsystem.vehicle.dto.*;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarModel;
import com.insurance.insurancemanagementsystem.vehicle.entity.Manufacturer;
import com.insurance.insurancemanagementsystem.vehicle.entity.Vehicle;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarDetailsRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarManufacturerRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarModelRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService implements AdminServiceInterface {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CarDetailsRepository carDetailsRepository;
    private CarModelRepository carModelRepository;
    private CarManufacturerRepository carManufacturerRepository;
    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;
    private InsuranceRepository insuranceRepository;

    @Override
    public ResponseEntity<String> AddEmployee(ClaimSpecialization specialization, EmployeeRequestDTO employeeRequestDTO) {
        User user = new User();
        if (userRepository.findByUsername(employeeRequestDTO.getUserName()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        user.setUsername(employeeRequestDTO.getUserName());
        user.setPassword(employeeRequestDTO.getPassword());
        user.setAccountStatus("ACTIVE");
        Role role = roleRepository.findByRoleName("ROLE_EMPLOYEE")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setGender(employeeRequestDTO.getGender());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setSpecialization(specialization);
        User user1 = userRepository.findByUsername(employeeRequestDTO.getUserName()).
                orElseThrow(() -> new RuntimeException("Not valid name"));
        employee.setUser(user);
        employeeRepository.save(employee);
        return ResponseEntity.ok("successfully");
    }

    @Override
    public ResponseEntity<Page<Employee>> AllEmployeeDetail(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<Page<Employee>> FindByEmployees(ClaimSpecialization specialization, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Employee> employees = employeeRepository.findBySpecialization(specialization, pageable);
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> UpdateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Not valid"));
        User user = employee.getUser();
        user.setUsername(employeeRequestDTO.getUserName());
        user.setPassword(employeeRequestDTO.getPassword());
        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setGender(employeeRequestDTO.getGender());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setPhone(employeeRequestDTO.getPhone());
        userRepository.save(user);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeResponseDTO responseDTO = mapToResponseDTO(savedEmployee);
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    public ResponseEntity<String> DeleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Not valid"));
        User user = employee.getUser();
        employeeRepository.delete(employee);
        if (user != null) {
            userRepository.delete(user);
        }

        return ResponseEntity.ok("delete successfully");
    }

    @Override
    public ResponseEntity<String> CarDeatilsUpdate(CarDetailsUpdateRequestDTO carDetailsRequestDTO) {
        CarDetails carDetails = carDetailsRepository.findById(carDetailsRequestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carDetailsRequestDTO.getId()));
        Manufacturer manufacturer = carManufacturerRepository.findById(carDetailsRequestDTO.getManufacturerlId())
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carDetailsRequestDTO.getManufacturerlId()));
        CarModel model = carModelRepository.findById(carDetailsRequestDTO.getModelId())
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carDetailsRequestDTO.getId()));
        carDetails.setModel(model);
        carDetails.setManufacturer(manufacturer);
        carDetails.setTransmission(carDetailsRequestDTO.getTransmissionType());
        carDetails.setFuelType(carDetailsRequestDTO.getFuelType());
        carDetails.setIdvValue(carDetailsRequestDTO.getIdvValue());
        carDetailsRepository.save(carDetails);

        return ResponseEntity.ok("Successfully");
    }

    @Override
    public ResponseEntity<String> UpdateCarModel(CarModelUpdateRequestDTO carModelUpdateRequestDTO) {
        CarModel carModel = carModelRepository.findById(carModelUpdateRequestDTO.getId()).
                orElseThrow(() -> new RuntimeException("Id  not Found"));
        Manufacturer manufacturer = carManufacturerRepository.findById(carModelUpdateRequestDTO.getManufacturer())
                .orElseThrow(() -> new RuntimeException("Id  not Found"));
        carModel.setName(carModelUpdateRequestDTO.getName());
        carModel.setManufacturer(manufacturer);
        carModel.setBodyType(carModelUpdateRequestDTO.getBodyType());
        carModel.setEngineCcRange(carModelUpdateRequestDTO.getEngineCcRange());
        carModel.setSeatingCapacity(carModelUpdateRequestDTO.getSeatingCapacity());
        carModelRepository.save(carModel);

        return ResponseEntity.ok("Update Successfully");
    }

    @Override
    public ResponseEntity<String> UpdateManufacturer(UpdateManufacturerDTO updateManufacturerDTO) {
        Manufacturer manufacturer = carManufacturerRepository.findById(updateManufacturerDTO.getId())
                .orElseThrow(() -> new RuntimeException("Id not Found"));
        manufacturer.setName(updateManufacturerDTO.getName());
        manufacturer.setActive(updateManufacturerDTO.getActive());
        carManufacturerRepository.save(manufacturer);
        return ResponseEntity.ok("Successfully");
    }

    @Override
    public ResponseEntity<String> VehicleUpdate(VehicleUpdateRequestDTO vehicleUpdateRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleUpdateRequestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Id not Found"));
        Manufacturer manufacturer = carManufacturerRepository.findById(vehicleUpdateRequestDTO.getManufacturer())
                .orElseThrow(() -> new RuntimeException("Id not Found"));
        CarDetails carDetails = carDetailsRepository.findById(vehicleUpdateRequestDTO.getCarDetails())
                .orElseThrow(() -> new RuntimeException("Id not Found"));
        vehicle.setManufacturer(manufacturer);
        vehicle.setCarDetails(carDetails);
        vehicle.setVehicleAge(vehicleUpdateRequestDTO.getVehicleAge());
        vehicle.setRegistrationNumber(vehicleUpdateRequestDTO.getRegistrationNumber());
        vehicle.setIdvValue(vehicleUpdateRequestDTO.getIdvValue());
        vehicle.setRegistrationDate(vehicleUpdateRequestDTO.getRegistrationDate());
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok("Successfully");
    }

    @Override
    public ResponseEntity<String> AddUserDetails(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequestDTO.getFirstName());
        customer.setLastName(customerRequestDTO.getLastName());
        customer.setDob(customerRequestDTO.getDob());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setAddress(customerRequestDTO.getAddress());
        customer.setGender(customerRequestDTO.getGender());
        customer.setMobile(customerRequestDTO.getMobile());
        customer.setKycStatus(customerRequestDTO.getKycStatus());
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not Found"));
        customer.setUser(user);
        customerRepository.save(customer);
        return ResponseEntity.ok("Successfully");
    }

    @Override
    public ResponseEntity<Page<Customer>> AllCustomerDetails(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return ResponseEntity.ok(customers);
    }

    @Override
    public ResponseEntity<Customer> ViewCustomer(String mobile) {
        Customer customer = customerRepository.findByMobile(mobile);
        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<String> UpdateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not Found"));
        customer.setFirstName(customerRequestDTO.getFirstName());
        customer.setLastName(customerRequestDTO.getLastName());
        customer.setDob(customerRequestDTO.getDob());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setAddress(customerRequestDTO.getAddress());
        customer.setGender(customerRequestDTO.getGender());
        customer.setMobile(customerRequestDTO.getMobile());
        customer.setKycStatus(customerRequestDTO.getKycStatus());
        User user = customer.getUser();
        customer.setUser(user);
        customerRepository.save(customer);
        return ResponseEntity.ok("Successfully");
    }

    @Override
    public ResponseEntity<String> DeleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not Found"));
        customerRepository.delete(customer);
        return ResponseEntity.ok("Successfully");
    }

    @Override
    public ResponseEntity<Page<Insurance>> viewAllInsurance(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Insurance> insurance = insuranceRepository.findAll(pageable);
        return ResponseEntity.ok(insurance);
    }

    @Override
    public ResponseEntity<String> UpdateInsurance(Long id, InsuranceRequestDTO insuranceRequestDTO) {
        return null;
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

