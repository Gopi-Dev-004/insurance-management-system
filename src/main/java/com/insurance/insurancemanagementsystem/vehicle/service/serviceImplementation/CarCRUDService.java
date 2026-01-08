package com.insurance.insurancemanagementsystem.vehicle.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.common.exception.ResourceNotFoundException;
import com.insurance.insurancemanagementsystem.vehicle.dto.CarDetailsRequestDTO;
import com.insurance.insurancemanagementsystem.vehicle.dto.CarModelRequestDTO;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarModel;
import com.insurance.insurancemanagementsystem.vehicle.entity.Manufacturer;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarDetailsRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarManufacturerRepository;
import com.insurance.insurancemanagementsystem.vehicle.repository.CarModelRepository;
import com.insurance.insurancemanagementsystem.vehicle.service.CarCRUDServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarCRUDService implements CarCRUDServiceInterface {

    private final CarManufacturerRepository manRef;
    private final CarModelRepository carModelRep;
    private final CarDetailsRepository carDetailsRep;

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
}
