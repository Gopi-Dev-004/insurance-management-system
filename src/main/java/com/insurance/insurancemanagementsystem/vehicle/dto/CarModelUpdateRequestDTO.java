package com.insurance.insurancemanagementsystem.vehicle.dto;

import com.insurance.insurancemanagementsystem.common.enums.BodyType;
import com.insurance.insurancemanagementsystem.common.enums.EngineCCRange;
import com.insurance.insurancemanagementsystem.common.enums.SeatingCapacity;
import com.insurance.insurancemanagementsystem.vehicle.entity.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarModelUpdateRequestDTO {
    private Long id;
    private String name;
    private Long manufacturer;
    private BodyType bodyType;
    private EngineCCRange engineCcRange;
    private SeatingCapacity seatingCapacity;
    private Boolean active;
}
