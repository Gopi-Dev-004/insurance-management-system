package com.insurance.insurancemanagementsystem.vehicle.controller;

import com.insurance.insurancemanagementsystem.vehicle.dto.CarDetailsRequestDTO;
import com.insurance.insurancemanagementsystem.vehicle.dto.CarModelRequestDTO;
import com.insurance.insurancemanagementsystem.vehicle.service.CarCRUDServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarCRUDController {

    private final CarCRUDServiceInterface carRef;

    //add new manufacturer -> company or brand

    @PostMapping("/add/{manufacturerName}")
    public ResponseEntity<String> addManufacturer(@PathVariable String manufacturerName) {
        String result = carRef.addManufacturer(manufacturerName);
        return ResponseEntity.ok(result);
    }

    //add new model -> required by manufacturer

    @PostMapping("/add/car_model")
    public ResponseEntity<String> addCarModel(@Valid @RequestBody CarModelRequestDTO dto) {
        String result = carRef.addCarModel(dto);
        return ResponseEntity.ok(result);
    }

    //add new model_details -> required by model
    @PostMapping("add/car_detials")
    public ResponseEntity<String> addCarDetails(@Valid @RequestBody CarDetailsRequestDTO dto) {
        String result = carRef.addCarDetails(dto);
        return ResponseEntity.ok(result);
    }

}

