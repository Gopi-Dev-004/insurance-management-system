package com.insurance.insurancemanagementsystem.customer.repository;

import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{
    Customer findByMobile(String mobile);
}
