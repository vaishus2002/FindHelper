package com.app.dto;

public class MapLocationDTO {

    private Long id;
    private String location;

    // Constructors
    public MapLocationDTO() {
    }

    public MapLocationDTO(Long id, String location) {
        this.id = id;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
