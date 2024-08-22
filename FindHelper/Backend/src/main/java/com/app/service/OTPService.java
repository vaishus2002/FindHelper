package com.app.service;

public interface OTPService {
	  void generateAndSendOTP(String mobileNumber);
	    boolean validateOTP(String mobileNumber, String otpCode);
}
