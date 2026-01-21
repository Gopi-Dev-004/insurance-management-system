package com.insurance.insurancemanagementsystem.claim.repository;

import com.insurance.insurancemanagementsystem.claim.entity.ClaimMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MediaRepository extends JpaRepository<ClaimMedia,Long> {
}
