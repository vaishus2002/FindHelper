package com.app.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.app.entity.AvailableTime;
import com.app.entity.CategoryEntity;
import com.app.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServiceProviderResponceDto {
	private String fname;
	private String lname;
	private String categoryName;
	private Integer experience;
	private Double costPerHour;
	private AvailableTime availabilityTime; // Assuming AvailableTime is an enum
	private String availableLocations;
	
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public AvailableTime getAvailabilityTime() {
		return availabilityTime;
	}
	public void setAvailabilityTime(AvailableTime availabilityTime) {
		this.availabilityTime = availabilityTime;
	}
	public String getAvailableLocations() {
		return availableLocations;
	}
	public void setAvailableLocations(String availableLocations) {
		this.availableLocations = availableLocations;
	}

}
