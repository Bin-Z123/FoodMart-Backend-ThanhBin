package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.CategoryCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.CategoryUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ApiResponse;
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
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryUpdateRequest request){
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.updateCategories(id,request));
        return apiResponse;
    }
    //Post
    @PostMapping("/category")
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreationRequest request){
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.createCategories(request));
        return apiResponse;
    }
    //Delete
    @DeleteMapping("/category/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategories(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Delete Success category with id: "+id);
        return apiResponse;
    }

}
