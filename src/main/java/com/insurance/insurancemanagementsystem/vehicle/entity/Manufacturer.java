package com.insurance.insurancemanagementsystem.vehicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "name" , nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = 'active', nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<CarModel> models;

}
