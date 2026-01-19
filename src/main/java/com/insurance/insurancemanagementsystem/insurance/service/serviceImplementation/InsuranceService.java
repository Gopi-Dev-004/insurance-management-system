package com.insurance.insurancemanagementsystem.insurance.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.common.enums.ClaimStatus;
import com.insurance.insurancemanagementsystem.common.enums.InsuranceStatus;
import com.insurance.insurancemanagementsystem.common.enums.PolicyStatus;
import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import com.insurance.insurancemanagementsystem.customer.repository.CustomerRepository;
import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import com.insurance.insurancemanagementsystem.insurance.repository.InsuranceRepository;
import com.insurance.insurancemanagementsystem.payment.entity.Payment;
import com.insurance.insurancemanagementsystem.policy.entity.Policy;
import com.insurance.insurancemanagementsystem.policy.repository.PolicyRepository;
import com.insurance.insurancemanagementsystem.vehicle.entity.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceService {
    private CustomerRepository customerRepository;
    private InsuranceRepository insuranceRepository;
    private PolicyRepository policyRepository;

    public String createInsurance(Long customerId, Payment payment) {

        Insurance insurance = new Insurance();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Id not Found"));

        insurance.setInsuranceStatus(InsuranceStatus.ACTIVE);
        insurance.setPayment(payment);
        Policy policy = payment.getPolicy();
        insurance.setPolicy(policy);
        Vehicle vehicle = policy.getVehicle();
        insurance.setVehicle(vehicle);
        insurance.setCustomer(customer);
        insurance.setClaimStatus(ClaimStatus.NOT_CLAIMED);
        insurance.setPolicyStartDate(policy.getStartDate());
        insurance.setPolicyEndDate(policy.getEndDate());

        insuranceRepository.save(insurance);

        return "Successfully";
    }

}
