package com.insurance.insurancemanagementsystem.policy.repository;

import com.insurance.insurancemanagementsystem.policy.entity.PolicyAddon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyAddonRepository extends JpaRepository<PolicyAddon,Long> {
}
