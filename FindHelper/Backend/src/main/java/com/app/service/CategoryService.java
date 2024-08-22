package com.app.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.customException.ResourceNotFoundException;
import com.app.dto.CategoryDTO;
import com.app.entity.CategoryEntity;
import com.app.repository.CategoryRepository;
@Transactional
@Service
public class CategoryService implements CategoryServiceInterface {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO, MultipartFile file) throws IOException {
		CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);

		// Handle image upload
		if (file != null && !file.isEmpty()) {
			categoryEntity.setImageData(file.getBytes());
			categoryEntity.setImageType(file.getContentType());
			categoryEntity.setImageName(file.getOriginalFilename());
		}

		CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
		return modelMapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO, MultipartFile file) throws IOException {
		CategoryEntity existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

		modelMapper.map(categoryDTO, existingCategory);

		// Handle image upload
		if (file != null && !file.isEmpty()) {
			existingCategory.setImageData(file.getBytes());
			existingCategory.setImageType(file.getContentType());
			existingCategory.setImageName(file.getOriginalFilename());
		}

		CategoryEntity updatedCategory = categoryRepository.save(existingCategory);
		return modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryById(Long id) {
		CategoryEntity categoryEntity = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
		return mapToCategoryDTO(categoryEntity);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll().stream().map(this::mapToCategoryDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
		categoryRepository.deleteById(id);
	}

//    public List<ServiceProvider> getAllServiceProvidersByCategoryId(Long categoryId) {
//        CategoryEntity category = categoryRepository.giveEntity(categoryId);
//                //.orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
//        return category.getProviders();
//    }

	// Helper method to map CategoryEntity to CategoryDTO
	private CategoryDTO mapToCategoryDTO(CategoryEntity categoryEntity) {
		CategoryDTO categoryDTO = modelMapper.map(categoryEntity, CategoryDTO.class);
		categoryDTO.setImageData(categoryEntity.getImageData());
		categoryDTO.setImageName(categoryEntity.getImageName());
		categoryDTO.setImageType(categoryEntity.getImageType());
		return categoryDTO;
	}

	@Override
	public CategoryEntity findById(Long id) {

		return categoryRepository.giveEntity(id);
	}
}
