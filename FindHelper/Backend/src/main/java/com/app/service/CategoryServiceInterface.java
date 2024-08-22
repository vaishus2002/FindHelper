package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CategoryDTO;
import com.app.entity.CategoryEntity;

public interface CategoryServiceInterface {
	public CategoryDTO createCategory(CategoryDTO categoryDTO, MultipartFile file) throws IOException;

	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO, MultipartFile file) throws IOException;

	public CategoryDTO getCategoryById(Long id);

	public List<CategoryDTO> getAllCategories();

	public void deleteCategory(Long id);

	public CategoryEntity findById(Long id);

}
