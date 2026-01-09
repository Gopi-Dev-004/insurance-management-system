package com.insurance.insurancemanagementsystem.vehicle.repository;

import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import com.insurance.insurancemanagementsystem.vehicle.entity.CarModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailsRepository  extends JpaRepository<CarDetails,Long> {

    @Query("""
            SELECT d FROM CarDetails d WHERE d.model = :model
            """)
    Page<CarDetails> getCarDetails(@Param("model") CarModel carModel , Pageable pageable);



}
