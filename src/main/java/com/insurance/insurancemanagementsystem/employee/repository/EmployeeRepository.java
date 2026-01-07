package com.insurance.insurancemanagementsystem.employee.repository;

import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>
{
}
