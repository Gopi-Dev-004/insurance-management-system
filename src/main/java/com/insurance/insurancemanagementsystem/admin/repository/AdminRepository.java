package com.insurance.insurancemanagementsystem.admin.repository;

import com.insurance.insurancemanagementsystem.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>
{

}
