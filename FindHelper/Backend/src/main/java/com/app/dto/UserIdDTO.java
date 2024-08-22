package com.app.dto;



import java.io.Serializable;

public class UserIdDTO implements Serializable {

    private Long id;

    // Default constructor
    public UserIdDTO() {
    }

    // Constructor with parameters
    public UserIdDTO(Long id) {
        this.id = id;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
               "id=" + id +
               '}';
    }
}

