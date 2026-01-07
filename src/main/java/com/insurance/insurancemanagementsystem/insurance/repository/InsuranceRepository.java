package com.insurance.insurancemanagementsystem.insurance.repository;

import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Long>
{
}
