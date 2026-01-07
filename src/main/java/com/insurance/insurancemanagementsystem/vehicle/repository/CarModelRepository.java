package com.insurance.insurancemanagementsystem.vehicle.repository;

import com.insurance.insurancemanagementsystem.vehicle.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository  extends JpaRepository<CarModel,Long> {
}
