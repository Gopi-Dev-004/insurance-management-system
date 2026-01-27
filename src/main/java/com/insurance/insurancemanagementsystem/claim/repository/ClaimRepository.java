package com.insurance.insurancemanagementsystem.claim.repository;

import com.insurance.insurancemanagementsystem.claim.entity.Claim;
import com.insurance.insurancemanagementsystem.common.enums.ClaimStatus;
import com.insurance.insurancemanagementsystem.common.enums.ClaimType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Long> {

    Page<Claim> findAllByClaimTypeAndClaimStatus(ClaimType claimType, ClaimStatus claimStatus, Pageable pageable);
}
