package com.insurance.insurancemanagementsystem.vehicle.entity;


import com.insurance.insurancemanagementsystem.common.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.tags.EscapeBodyTag;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_model")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_cc_range", nullable = false)
    private EngineCCRange engineCcRange;

    @Enumerated(EnumType.STRING)
    @Column(name = "seating_capacity", nullable = false)
    private SeatingCapacity seatingCapacity;

    @Column(name = "active", nullable = false)
    private Boolean active = true;


}
