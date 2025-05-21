package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.CategoryCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.CategoryUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.CategoryResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Category;
import com.poly.ASSIGNMENT_JAVA5.mapper.CategoryMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.CategoryRepository;
import com.poly.ASSIGNMENT_JAVA5.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
  CategoryRepository categoryRepository;
  CategoryMapper categoryMapper;
  private final ProductRepository productRepository;

  @Autowired
  public CategoryService(
      CategoryRepository categoryRepository,
      CategoryMapper categoryMapper,
      ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
    this.productRepository = productRepository;
  }

  // Get
  public List<CategoryResponse> getAll() {
    return categoryRepository.findAll().stream()
        .map(categoryMapper::toCategoryResponse)
        .collect(Collectors.toList());
  }

  public Optional<CategoryResponse> getCategoryByIDs(Long id) {
    return categoryRepository.findById(id).map(categoryMapper::toCategoryResponse);
  }

  //    Put
  public CategoryResponse updateCategories(Long id, CategoryUpdateRequest request) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
    categoryMapper.updateCategory(category, request);
    return categoryMapper.toCategoryResponse(categoryRepository.save(category));
  }

  // POST
  public CategoryResponse createCategories(CategoryCreationRequest request) {
    Category category = categoryMapper.toCategory(request);
    return categoryMapper.toCategoryResponse(categoryRepository.save(category));
  }

  // DELETE
  public void deleteCategories(Long id) {
    productRepository.deleteById(id);
  }
}
