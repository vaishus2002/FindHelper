package com.app.dto;

import com.app.entity.AvailableTime;

public class ServiceUpdateDto {

    
    private String fname;
    private String lname;
    private Integer experience;
    private Double costPerHour;
    private String availableLocations;
    private AvailableTime availabilityTime; // Use AvailableTime enum directly
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Double getCostPerHour() {
		return costPerHour;
	}
	public void setCostPerHour(Double costPerHour) {
		this.costPerHour = costPerHour;
	}
	public String getAvailableLocations() {
		return availableLocations;
	}
	public void setAvailableLocations(String availableLocations) {
		this.availableLocations = availableLocations;
	}
	public AvailableTime getAvailabilityTime() {
		return availabilityTime;
	}
	public void setAvailabilityTime(AvailableTime availabilityTime) {
		this.availabilityTime = availabilityTime;
	}
	@Override
	public String toString() {
		return "ServiceUpdateDto [fname=" + fname + ", lname=" + lname + ", experience=" + experience + ", costPerHour="
				+ costPerHour + ", availableLocations=" + availableLocations + ", availabilityTime=" + availabilityTime
				+ "]";
	}
	
    

}
