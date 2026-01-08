package com.insurance.insurancemanagementsystem.vehicle.dto;

import com.insurance.insurancemanagementsystem.common.enums.BodyType;
import com.insurance.insurancemanagementsystem.common.enums.EngineCCRange;
import com.insurance.insurancemanagementsystem.common.enums.SeatingCapacity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarModelRequestDTO {

    @NotBlank(message = "Car model name is required")
    @Size(min = 2, max = 50, message = "Car model name must be between 2 and 50 characters")
        private String carModelName;

    @NotNull(message = "Manufacturer id is required")
    private Long manufacturerId;

    @NotNull(message = "Body type is required")
    private BodyType bodyType;

    @NotNull(message = "Engine CC range is required")
    private EngineCCRange engineCCRange;

    @NotNull(message = "Seating capacity is required")
    private SeatingCapacity seatingCapacity;
}
