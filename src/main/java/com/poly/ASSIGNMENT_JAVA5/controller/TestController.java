package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.response.CategoryResponse;
import com.poly.ASSIGNMENT_JAVA5.service.CategoryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api")
public class TestController {
    CategoryService categoryService;
    @Autowired
    public TestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
//Get
    @RequestMapping("/category")
    public List<CategoryResponse> getAllCategory() {
        return categoryService.getAll();
    }
}
