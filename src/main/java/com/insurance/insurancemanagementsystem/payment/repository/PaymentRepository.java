package com.insurance.insurancemanagementsystem.payment.repository;

import com.insurance.insurancemanagementsystem.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>
{
}
