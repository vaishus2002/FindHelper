package com.app.dto;



import java.io.Serializable;

public class CategoryIdDTO implements Serializable {

    private Long id;

    // Default constructor
    public CategoryIdDTO() {
    }

    // Constructor with parameters
    public CategoryIdDTO(Long id) {
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
        return "CategoryDTO{" +
               "id=" + id +
               '}';
    }
}

