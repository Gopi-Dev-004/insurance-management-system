package com.insurance.insurancemanagementsystem.policy.repository;

import com.insurance.insurancemanagementsystem.policy.entity.Addon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonRepository extends JpaRepository<Addon,Long> {
}
