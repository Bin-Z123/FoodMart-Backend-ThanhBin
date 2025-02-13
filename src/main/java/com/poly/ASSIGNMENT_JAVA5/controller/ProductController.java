package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ProductResponse;
import com.poly.ASSIGNMENT_JAVA5.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api")
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Get
    @GetMapping("/product")
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public Optional<ProductResponse> getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //Put
    @PutMapping("/product/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request){
        return productService.updateProducts(id,request);
    }
}
