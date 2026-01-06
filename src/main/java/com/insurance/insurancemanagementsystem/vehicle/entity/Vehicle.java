package com.insurance.insurancemanagementsystem.vehicle.entity;

import com.insurance.insurancemanagementsystem.common.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column( name = "registration_number", nullable = false, unique = true,length = 20)
    private String registrationNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel model;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_cc_range", nullable = false)
    private EngineCCRange engineCcRange;

    @Enumerated(EnumType.STRING)
    @Column(name = "seating_capacity", nullable = false)
    private SeatingCapacity seatingCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private BodyType bodyType;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "vehicle_age")
    private Integer vehicleAge;

    @Column(name = "idv_value", nullable = false)
    private BigDecimal idvValue;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        calculateVehicleAge();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        calculateVehicleAge();
    }

    private void calculateVehicleAge() {
        if (registrationDate != null) {
            this.vehicleAge = Period.between(registrationDate, LocalDate.now()).getYears();
        }
    }








}
