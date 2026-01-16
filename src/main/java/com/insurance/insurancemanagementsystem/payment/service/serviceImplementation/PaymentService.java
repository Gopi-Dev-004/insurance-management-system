package com.insurance.insurancemanagementsystem.payment.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.common.enums.PaymentMethod;
import com.insurance.insurancemanagementsystem.common.enums.PaymentStatus;
import com.insurance.insurancemanagementsystem.payment.entity.Payment;
import com.insurance.insurancemanagementsystem.payment.repository.PaymentRepository;
import com.insurance.insurancemanagementsystem.policy.entity.Policy;
import com.insurance.insurancemanagementsystem.policy.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;
    private PolicyRepository policyRepository;
    public  void SavePayment(Long id){
        Payment  payment=new Payment();
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(PaymentMethod.UPI);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentReference(generatePaymentReference());

        payment.setTransactionId(generateTransactionId());
      Policy policy= policyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("id not fount"));
      payment.setPolicy(policy);
        payment.setAmountPaid(policy.getBasePremium());
      paymentRepository.save(payment);
    }
    public String generatePaymentReference() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }
    public String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis() + "-"
                + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }


}
