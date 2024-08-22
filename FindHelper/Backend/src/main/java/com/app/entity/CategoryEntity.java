package com.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    @Lob
    private byte[] imageData;

    private String imageType;
    private String imageName;
//    @OneToMany(mappedBy = "category",orphanRemoval = true)
//    
//    private List<ServiceProvider> providers=new ArrayList<>();

    // Getters and Setters
    
    public Long getCategoryId() {
        return categoryId;
    }

//    public List<ServiceProvider> getProviders() {
//		return providers;
//	}
//
//	public void setProviders(List<ServiceProvider> providers) {
//		this.providers = providers;
//	}

	public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
