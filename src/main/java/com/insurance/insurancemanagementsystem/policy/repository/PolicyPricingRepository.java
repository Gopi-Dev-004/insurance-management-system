package com.insurance.insurancemanagementsystem.policy.repository;

import com.insurance.insurancemanagementsystem.common.enums.PolicyType;
import com.insurance.insurancemanagementsystem.policy.entity.PolicyPricing;
import jakarta.validation.constraints.NotNull;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyPricingRepository extends JpaRepository<PolicyPricing,Long> {

    PolicyPricing findByPolicyType(@NotNull(message = "Policy type is required") PolicyType policyType);
}
