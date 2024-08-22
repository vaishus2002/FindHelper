package com.app.dto;

import java.io.Serializable;

import com.app.entity.AvailableTime;

public class ServiceProviderDTO implements Serializable {

    private Long id;
    private String fname;
    private String lname;
    private CategoryIdDTO category;  // Use CategoryDTO instead of Category
    private UserIdDTO user;          // Use UserDTO instead of User
    private Integer experience;
    private Double costPerHour;
    private String availableLocations;
    private AvailableTime availabilityTime; // Use AvailableTime enum directly

    public AvailableTime getAvailabilityTime() {
		return availabilityTime;
	}

	public void setAvailabilityTime(AvailableTime availabilityTime) {
		this.availabilityTime = availabilityTime;
	}

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CategoryIdDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryIdDTO category) {
        this.category = category;
    }

    public UserIdDTO getUser() {
        return user;
    }

    public void setUser(UserIdDTO user) {
        this.user = user;
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

    

    @Override
    public String toString() {
        return "ServiceProviderDTO{" +
               "id=" + id +
               ", fname='" + fname + '\'' +
               ", lname='" + lname + '\'' +
               ", category=" + category +
               ", user=" + user +
               ", experience=" + experience +
               ", costPerHour=" + costPerHour +
               ", availableLocations='" + availableLocations + '\'' +
               ", availableTime=" + availabilityTime +
               '}';
    }
}
