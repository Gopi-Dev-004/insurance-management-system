package com.insurance.insurancemanagementsystem.email.service;

import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInsuranceDetailsEmail(String toEmail, Insurance insurance)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Insurance Details Confirmation");

        String htmlContent =
                "<h2>Insurance Details</h2>" +

                        "<h3>Customer Information</h3>" +
                        "<p><b>Name:</b> " + insurance.getCustomer().getFirstName()+insurance.getCustomer().getLastName() + "</p>" +
                        "<p><b>Email:</b> " + insurance.getCustomer().getEmail() + "</p>" +

                        "<h3>Vehicle Information</h3>" +
                        "<p><b>Vehicle Number:</b> " + insurance.getVehicle().getRegistrationNumber()+ "</p>" +
                        "<p><b>Model:</b> " + insurance.getVehicle().getCarDetails().getModel().getBodyType() + "</p>" +

                        "<h3>Policy Information</h3>" +
                        "<p><b>Policy Name:</b> " + insurance.getPolicy().getPolicyType() + "</p>" +
                        "<p><b>Policy Start Date:</b> " + insurance.getPolicyStartDate() + "</p>" +
                        "<p><b>Policy End Date:</b> " + insurance.getPolicyEndDate() + "</p>" +
                        "<p><b>Policy Total Amound:</b> " + insurance.getPolicy().getBasePremium() + "</p>" +

                        "<h3>Payment Information</h3>" +
                        "<p><b>Amount Paid:</b> ₹" + insurance.getPayment().getAmountPaid() + "</p>" +
                        "<p><b>Payment Status:</b> " + insurance.getPayment().getPaymentStatus() + "</p>" +

                        "<h3>Insurance Status</h3>" +
                        "<p><b>Insurance Status:</b> " + insurance.getInsuranceStatus() + "</p>" +
                        "<p><b>Claim Status:</b> " + insurance.getClaimStatus() + "</p>" +

                        "<br><p>Thank you for choosing our Insurance Service.</p>" +
                        "<p>Regards,<br><b>Insurance Management Team</b></p>";

        helper.setText(htmlContent, true);


        mailSender.send(message);
    }

}

