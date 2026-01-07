package com.insurance.insurancemanagementsystem.customer.repository;

import com.insurance.insurancemanagementsystem.customer.entity.CustomerKyc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerKycRepository extends JpaRepository<CustomerKyc,Long> {
}
