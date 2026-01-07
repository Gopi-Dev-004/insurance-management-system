package com.insurance.insurancemanagementsystem.claim.repository;

import com.insurance.insurancemanagementsystem.claim.entity.ClaimAssignmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimAssignmentHistoryRepository extends JpaRepository<ClaimAssignmentHistory,Long> {
}
