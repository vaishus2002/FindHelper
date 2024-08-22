package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.OTP;

public interface OTPRepository extends JpaRepository<OTP, Long>{
	 OTP findByMobileNumberAndOtpCode(String mobileNumber, String otpCode);
}
