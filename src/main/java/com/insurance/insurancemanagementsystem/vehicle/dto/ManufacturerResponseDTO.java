package com.insurance.insurancemanagementsystem.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ManufacturerResponseDTO {

    private Long manufacturer_id;

    private String manufacturer_name;
}
