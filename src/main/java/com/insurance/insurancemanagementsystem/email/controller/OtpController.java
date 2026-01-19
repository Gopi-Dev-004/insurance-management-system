package com.insurance.insurancemanagementsystem.email.controller;


import com.insurance.insurancemanagementsystem.email.service.EmailService;
import com.insurance.insurancemanagementsystem.email.service.OtpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String email) throws MessagingException {
        String otp = otpService.generateOtp(email);

        return "OTP Sent Successfully";
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        if (otpService.verifyOtp(email,otp)) {
            return "OTP Verified Successfully";
        } else {
            return "Invalid OTP";
        }
    }
}


