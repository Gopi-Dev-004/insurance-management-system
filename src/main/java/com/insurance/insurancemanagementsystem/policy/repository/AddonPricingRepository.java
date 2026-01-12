package com.insurance.insurancemanagementsystem.policy.repository;

import com.insurance.insurancemanagementsystem.common.enums.AddonType;
import com.insurance.insurancemanagementsystem.policy.entity.AddonPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AddonPricingRepository extends JpaRepository<AddonPricing,Long> {
    AddonPricing findByAddonType(AddonType type);

    List<AddonPricing> findByActiveTrue();
}
