package com.insurance.insurancemanagementsystem.policy.repository;

import com.insurance.insurancemanagementsystem.policy.entity.CarAgeDepreciation;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarAgeDepreciationRepository extends JpaRepository<CarAgeDepreciation,Long> {

    CarAgeDepreciation findByAge(Integer age);
}
