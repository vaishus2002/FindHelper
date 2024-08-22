package com.app.dto;

public class CategoryDTO {

    private Long categoryId;
    private String categoryName;

    // Fields for image data and metadata
    private String imageName;
    private String imageType;
    private byte[] imageData;

    // Getters and Setters

   

    public String getCategoryName() {
        return categoryName;
    }

   

	



	public Long getCategoryId() {
		return categoryId;
	}







	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}







	public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
