package com.insurance.insurancemanagementsystem.vehicle.service;

import com.insurance.insurancemanagementsystem.vehicle.dto.CarDetailsRequestDTO;
import com.insurance.insurancemanagementsystem.vehicle.dto.CarModelRequestDTO;
import jakarta.validation.Valid;

public interface CarCRUDServiceInterface {
    String addManufacturer(String manufacturerName);

    String addCarModel(CarModelRequestDTO dto);

    String addCarDetails(@Valid CarDetailsRequestDTO dto);
}
