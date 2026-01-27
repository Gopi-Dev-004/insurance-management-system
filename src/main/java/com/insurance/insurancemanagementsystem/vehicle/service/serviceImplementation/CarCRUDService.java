package com.insurance.insurancemanagementsystem.vehicle.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.common.exception.ResourceNotFoundException;
import com.insurance.insurancemanagementsystem.vehicle.dto.*;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarModel;
import com.insurance.insurancemanagementsystem.vehicle.entity.Manufacturer;
import com.insurance.insurancemanagementsystem.vehicle.entity.Vehicle;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarDetailsRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarManufacturerRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarModelRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.VehicleRepository;
import com.insurance.insurancemanagementsystem.vehicle.service.CarCRUDServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.insurance.insurancemanagementsystem.common.util.PolicyUtil.calculateCarAge;

@Service
@AllArgsConstructor
public class CarCRUDService implements CarCRUDServiceInterface {

    private final CarManufacturerRepository manRef;
    private final CarModelRepository carModelRep;
    private final CarDetailsRepository carDetailsRep;
    private VehicleRepository vehicleRepository;

    @Override
    public String addManufacturer(String manufacturerName) {

        Manufacturer newManufacturer = new Manufacturer();

        newManufacturer.setName(manufacturerName);

        Manufacturer manufacturer = manRef.save(newManufacturer);
        if (manufacturer != null)
            return "New Manufacturer is created successfully...";
        return "Something wrong";
    }

    @Override
    public String addCarModel(CarModelRequestDTO dto) {

        CarModel newCarModel = new CarModel();

        Manufacturer manufacturer = manRef.findById(dto.getManufacturerId()).orElseThrow(() -> new ResourceNotFoundException("Manufacturer id can't find"));


        newCarModel.setName(dto.getCarModelName());
        newCarModel.setManufacturer(manufacturer);
        newCarModel.setBodyType(dto.getBodyType());
        newCarModel.setEngineCcRange(dto.getEngineCCRange());
        newCarModel.setSeatingCapacity(dto.getSeatingCapacity());

        CarModel carModel = carModelRep.save(newCarModel);

        if (carModel != null)
            return "Car model created successfully....";
        return "Something wrong...!";


    }

    @Override
    public String addCarDetails(CarDetailsRequestDTO dto) {

        CarDetails newCarDetails = new CarDetails();

        CarModel carModel = carModelRep.findById(dto.getModelId()).orElseThrow(() -> new ResourceNotFoundException("Car Model can't find..."));

        newCarDetails.setModel(carModel);
        newCarDetails.setManufacturer(carModel.getManufacturer());
        newCarDetails.setTransmission(dto.getTransmissionType());
        newCarDetails.setFuelType(dto.getFuelType());
        newCarDetails.setIdvValue(dto.getIdvValue());

        CarDetails carDetails = carDetailsRep.save(newCarDetails);

        if (carDetails != null)
            return "CarDetails added successfully...";
        return "Something wrong...!";
    }

    @Override
    public Page<ManufacturerResponseDTO> getManufacturers(int pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber, 5);

        Page<Manufacturer> manufacturers = manRef.getManufacturers(pageable);

        return manufacturers.map(this::manufacturerMap);

    }

    @Override
    public Page<CarModelsResponseDTO> getCarModels(Long manufacturerId, int pageNumber) {
        Manufacturer manufacturer = manRef.findById(manufacturerId).orElseThrow(
                () -> new ResourceNotFoundException("Manufacturer not found...!"));

        Pageable pageable = PageRequest.of(pageNumber, 5);

        Page<CarModel> carModel = carModelRep.getCarModels(manufacturer, pageable);

        return carModel.map(this::carModelMap);

    }

    @Override
    public Page<CarDetailsResponseDTO> getCarDetails(Long modelId, int pageNumber) {

        CarModel carModel = carModelRep.findById(modelId).orElseThrow(() -> new ResourceNotFoundException("Car model not foud..."));

        Pageable pageable = PageRequest.of(pageNumber, 5);

        Page<CarDetails> carDetails1 = carDetailsRep.getCarDetails(carModel, pageable);

        return carDetails1.map(this::carDetailsMap);
    }

    private ManufacturerResponseDTO manufacturerMap(Manufacturer manufacturer) {

        ManufacturerResponseDTO dto = new ManufacturerResponseDTO();
        dto.setManufacturer_id(manufacturer.getId());
        dto.setManufacturer_name(manufacturer.getName());
        return dto;

    }

    private CarModelsResponseDTO carModelMap(CarModel carModel) {

        CarModelsResponseDTO dto = new CarModelsResponseDTO();

        dto.setModel_id(carModel.getId());
        dto.setModel_name(carModel.getName());

        return dto;
    }

    private CarDetailsResponseDTO carDetailsMap(CarDetails carDetails) {
        CarDetailsResponseDTO dto = new CarDetailsResponseDTO();

        dto.setId(carDetails.getId());
        dto.setTransmission(carDetails.getTransmission());
        dto.setFuelType(carDetails.getFuelType());


        return dto;
    }

    public Vehicle saveVehicle(CarDetails carDetails, String carRegisterNumber, LocalDate vehicleRegistrationDate, int carAge, BigDecimal idv) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(carRegisterNumber);
        vehicle.setVehicleAge(carAge);
        vehicle.setManufacturer(carDetails.getManufacturer());
        vehicle.setCarDetails(carDetails);
        vehicle.setIdvValue(idv);
        vehicle.setRegistrationDate(vehicleRegistrationDate);
        vehicle.setRegistrationNumber(generateRegisterNumber());

        Vehicle newVehicle = vehicleRepository.save(vehicle);

        return newVehicle;
    }

    private String generateRegisterNumber() {
        return String.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits())).substring(0, 10);
    }
}
