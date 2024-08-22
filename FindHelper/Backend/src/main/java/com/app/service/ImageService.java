package com.app.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.ImageEntity;
import com.app.repository.ImageRepository;

@Service
public class ImageService implements ImageServiceInterface {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public ImageEntity saveImage(MultipartFile file) throws IOException {
		ImageEntity image = new ImageEntity();
		image.setData(file.getBytes());
		image.setType(file.getContentType());
		image.setName(file.getOriginalFilename());
		return imageRepository.save(image);
	}

	@Override
	public ImageEntity getImage(Long id) {
		return imageRepository.findById(id).orElse(null);
	}
}
