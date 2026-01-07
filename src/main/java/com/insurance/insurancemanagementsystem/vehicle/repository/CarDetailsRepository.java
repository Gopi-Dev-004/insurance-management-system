package com.insurance.insurancemanagementsystem.vehicle.repository;

import com.insurance.insurancemanagementsystem.vehicle.entity.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailsRepository  extends JpaRepository<CarDetails,Long> {
}
