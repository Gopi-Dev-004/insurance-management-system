package com.insurance.insurancemanagementsystem.vehicle.dto;

import com.insurance.insurancemanagementsystem.common.enums.FuelType;
import com.insurance.insurancemanagementsystem.common.enums.Transmission;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class CarDetailsRequestDTO {

    @NotNull(message = "Car model id is required")
    private Long modelId;

    @NotNull(message = "Transmission type is required")
    private Transmission transmissionType;

    @NotNull(message = "Fuel type is required")
    private FuelType fuelType;

    @NotNull(message = "IDV value is required")
    @Positive(message = "IDV value must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "IDV value format is invalid")
    private BigDecimal idvValue;
}
