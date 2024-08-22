package com.app.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CategoryDTO;
import com.app.service.CategoryServiceInterface;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryServiceInterface categoryService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/upload")
	public ResponseEntity<?> uploadCategoryWithImage(@RequestParam("categoryName") String categoryName,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryName(categoryName);
			categoryService.createCategory(categoryDTO, file);
			return ResponseEntity.ok("Category and image uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error uploading category and image: " + e.getMessage());
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestParam("categoryName") String categoryName,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryName(categoryName);
		try {
			categoryService.updateCategory(id, categoryDTO, file);
		} catch (IOException e) {
			return ResponseEntity.status(500).body(null);

		}
		return ResponseEntity.ok("Category and image updated successfully");

	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
		try {
			CategoryDTO categoryDTO = categoryService.getCategoryById(id);
			return ResponseEntity.ok(categoryDTO);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'PROVIDER')")
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		try {
			List<CategoryDTO> categories = categoryService.getAllCategories();
			return ResponseEntity.ok(categories);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		try {
			categoryService.deleteCategory(id);
			return ResponseEntity.ok("Category deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error deleting category: " + e.getMessage());
		}
	}

//    @GetMapping("/{categoryId}/providers")
//    public ResponseEntity<List<ServiceProvider>> getAllServiceProvidersByCategoryId(@PathVariable Long categoryId) {
//        try {
//            List<ServiceProvider> providers = categoryService.getAllServiceProvidersByCategoryId(categoryId);
//            return ResponseEntity.ok(providers);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
}
