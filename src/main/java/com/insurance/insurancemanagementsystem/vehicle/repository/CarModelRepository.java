package com.insurance.insurancemanagementsystem.vehicle.repository;

import com.insurance.insurancemanagementsystem.vehicle.dto.CarModelsResponseDTO;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarModel;
import com.insurance.insurancemanagementsystem.vehicle.entity.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository  extends JpaRepository<CarModel,Long> {

    @Query("""
             SELECT m FROM CarModel m WHERE m.manufacturer = :manufacturer
            """)
    Page<CarModel> getCarModels(@Param("manufacturer") Manufacturer manufacturer, Pageable pageable);
}
