package com.insurance.insurancemanagementsystem.vehicle.dto;

import com.insurance.insurancemanagementsystem.common.enums.FuelType;
import com.insurance.insurancemanagementsystem.common.enums.Transmission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CarDetailsResponseDTO {

    private Long id;

    private Transmission transmission;

    private FuelType fuelType;
}
