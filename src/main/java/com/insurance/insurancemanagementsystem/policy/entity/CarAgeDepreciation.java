package com.insurance.insurancemanagementsystem.policy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "car_age_depreciation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarAgeDepreciation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Car age must not be null")
    @Min(value = 0, message = "Car age cannot be negative")
    @Column(nullable = false, unique = true)
    private Integer age;

    @NotNull(message = "Depreciation percentage is required")
    @DecimalMin(value = "0.0", message = "Depreciation must be >= 0")
    @DecimalMax(value = "100.0", message = "Depreciation must be <= 100")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal depreciationPercentage;

    @Column(nullable = false)
    private boolean active = true;
}
