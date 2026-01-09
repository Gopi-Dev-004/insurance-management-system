package com.insurance.insurancemanagementsystem.vehicle.service;

import com.insurance.insurancemanagementsystem.vehicle.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface CarCRUDServiceInterface {
    String addManufacturer(String manufacturerName);

    String addCarModel(CarModelRequestDTO dto);

    String addCarDetails(@Valid CarDetailsRequestDTO dto);

    Page<ManufacturerResponseDTO> getManufacturers(int pageNumber);

    Page<CarModelsResponseDTO> getCarModels(Long manufacturerId, int pageNumber);

    Page<CarDetailsResponseDTO> getCarDetails(Long modelId, int pageNumber);
}
