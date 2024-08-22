package com.app.controllers;

import com.app.dto.OtpDTO;
import com.app.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/request")
    public ResponseEntity<String> requestOTP(@RequestBody OtpDTO otpRequestDTO) {
        try {
            System.out.println("OTP Request for: " + otpRequestDTO.getMobileNumber());
            otpService.generateAndSendOTP(otpRequestDTO.getMobileNumber());
            return ResponseEntity.ok("OTP sent successfully.");
        } catch (Exception e) {
            // Log the error and return a meaningful response
            System.err.println("Error during OTP request: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to send OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestParam String mobileNumber, @RequestParam String otpCode) {
        try {
            boolean isValid = otpService.validateOTP(mobileNumber, otpCode);
            if (isValid) {
                System.out.println("OTP verified for: " + mobileNumber);
                return ResponseEntity.ok("OTP verified successfully.");
            } else {
                return ResponseEntity.status(400).body("Invalid or expired OTP.");
            }
        } catch (Exception e) {
            // Log the error and return a meaningful response
            System.err.println("Error during OTP verification: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to verify OTP: " + e.getMessage());
        }
    }
}
