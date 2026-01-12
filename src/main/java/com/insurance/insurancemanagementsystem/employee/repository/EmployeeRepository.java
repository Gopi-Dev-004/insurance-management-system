package com.insurance.insurancemanagementsystem.employee.repository;

import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>
{
    Page<Employee> findBySpecialization(ClaimSpecialization specialization, Pageable pageable);
}
