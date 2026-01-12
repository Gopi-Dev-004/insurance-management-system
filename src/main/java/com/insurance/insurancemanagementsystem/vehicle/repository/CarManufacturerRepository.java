package com.insurance.insurancemanagementsystem.vehicle.repository;

import com.insurance.insurancemanagementsystem.vehicle.dto.ManufacturerResponseDTO;
import com.insurance.insurancemanagementsystem.vehicle.entity.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarManufacturerRepository extends JpaRepository<Manufacturer,Long> {

    @Query("""
                    SELECT m FROM  Manufacturer m
            """)
    Page<Manufacturer> getManufacturers(Pageable pageable);

}
