package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.ImageEntity;

public interface ImageServiceInterface {
	public ImageEntity saveImage(MultipartFile file) throws IOException;

	public ImageEntity getImage(Long id);

}
