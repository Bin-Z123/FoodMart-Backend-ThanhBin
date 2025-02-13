package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.CategoryUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.CategoryResponse;
import com.poly.ASSIGNMENT_JAVA5.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api")
public class CategoryController {
    final CategoryService categoryService;
    //Get
    @GetMapping("/category")
    public List<CategoryResponse> getAllCategory() {
        return categoryService.getAll();
    }
    @GetMapping("/category/{id}")
    public Optional<CategoryResponse> getCategoryByID(@PathVariable Long id){
        return categoryService.getCategoryByIDs(id);
    }
//    Put
    @PutMapping("/category/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryUpdateRequest request){
        return categoryService.updateCategories(id,request);
    }
}
