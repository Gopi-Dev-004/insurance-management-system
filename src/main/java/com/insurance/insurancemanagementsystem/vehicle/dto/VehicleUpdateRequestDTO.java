package com.insurance.insurancemanagementsystem.vehicle.dto;

import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import com.insurance.insurancemanagementsystem.vehicle.entity.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleUpdateRequestDTO {
    private Long id;
    private String registrationNumber;
    private Long manufacturer;
    private Long carDetails;
    private LocalDate registrationDate;
    private Integer vehicleAge;
    private BigDecimal idvValue;

}
