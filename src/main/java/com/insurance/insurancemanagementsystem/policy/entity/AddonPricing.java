package com.insurance.insurancemanagementsystem.policy.entity;

import com.insurance.insurancemanagementsystem.common.enums.AddonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "addon_pricing")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddonPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Addon type must not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "addon_type", nullable = false, unique = true)
    private AddonType addonType;

    @NotNull(message = "Addon price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean active = true;
}

