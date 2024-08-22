package com.app.dto;



public class OtpDTO {
    private String mobileNumber;


    // Getters and Setters
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
	@Override
	public String toString() {
		return "OtpDTO [mobileNumber=" + mobileNumber + "]";
	}

    
}
