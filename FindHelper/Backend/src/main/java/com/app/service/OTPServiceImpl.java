package com.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.AuthenticationException;
import com.app.entity.OTP;
import com.app.repository.OTPRepository;
import com.app.utils.OTPGenerator;
import com.app.utils.SMSSender;

@Service
@Transactional
public class OTPServiceImpl implements OTPService {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private SMSSender smsSender;

    @Override
    @Transactional
    public void generateAndSendOTP(String mobileNumber) {
        String otpCode = OTPGenerator.generateOTP();
        OTP otp = new OTP();
        otp.setMobileNumber(mobileNumber);
        otp.setOtpCode(otpCode);
        otp.setExpirationTime(LocalDateTime.now().plusMinutes(5)); // Valid for 5 minutes
        otpRepository.save(otp);

        // Send OTP via SMS
        String message = "Your OTP is: " + otpCode;
        try {
            smsSender.sendSMS(mobileNumber, message);
        } catch (Exception e) {
            // Handle SMS sending failure
            throw new AuthenticationException("Failed to send OTP SMS: " + e.getMessage());
        }
    }

    @Override
    public boolean validateOTP(String mobileNumber, String otpCode) {
        OTP otp = otpRepository.findByMobileNumberAndOtpCode(mobileNumber, otpCode);
        if (otp != null && otp.getExpirationTime().isAfter(LocalDateTime.now())) {
            otpRepository.delete(otp); // OTP can be used only once
            return true;
        }
        return false;
    }
}
