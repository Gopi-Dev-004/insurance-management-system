package com.insurance.insurancemanagementsystem.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateManufacturerDTO {
    private Long id;
    private String name;
    private Boolean active;
}
