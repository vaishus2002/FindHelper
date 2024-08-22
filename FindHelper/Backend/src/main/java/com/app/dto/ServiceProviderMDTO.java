package com.app.dto;


import com.app.entity.AvailableTime;
import com.app.entity.CategoryEntity;

public class ServiceProviderMDTO {

    private Long id;
    private String fname;
    private String lname;
    private CategoryEntity category;
    private Integer experience;
    private Double costPerHour;
    private AvailableTime availabilityTime;
    private String availableLocations;
    private String mobileNumber;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long serviceProviderId) {
        this.id = serviceProviderId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String firstName) {
        this.fname = firstName;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lastName) {
        this.lname = lastName;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
