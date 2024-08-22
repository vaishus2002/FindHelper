package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
public class ServiceProvider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 100, message = "First name must be less than 100 characters")
    private String fname;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name must be less than 100 characters")
    private String lname;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true) // Ensure the user_id is unique
    @JsonIgnore
    
    private User user;

    @NotNull(message = "Experience cannot be null")
    @Positive(message = "Experience must be a positive integer")
    private Integer experience;

//    @Lob
//    @NotNull(message = "Photo cannot be null")
//    private byte[] photo;  // Store binary data

    @NotNull(message = "Cost per hour cannot be null")
    @Positive(message = "Cost per hour must be a positive value")
    private Double costPerHour;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Availability time cannot be null")
    private AvailableTime availabilityTime;  // Assuming AvailableTime is an enum

    @NotBlank(message = "Available locations cannot be blank")
    @Size(max = 200, message = "Available locations must be less than 200 characters")
    private String availableLocations;

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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

//    public byte[] getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(byte[] photo) {
//        this.photo = photo;
//    }

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

	@Override
	public String toString() {
		return "ServiceProvider [id=" + id + ", fname=" + fname + ", lname=" + lname + ", experience=" + experience
				+ ", costPerHour=" + costPerHour + ", availabilityTime=" + availabilityTime + ", availableLocations="
				+ availableLocations + "]";
	}
    
}
