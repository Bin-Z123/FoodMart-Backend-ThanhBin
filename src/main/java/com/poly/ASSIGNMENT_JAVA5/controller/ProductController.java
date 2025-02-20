package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ApiResponse;
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
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request){
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.updateProducts(id,request));
        return apiResponse;
    }

//    POST
    @PostMapping("/product")
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request){
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.createProducts(request));
        return apiResponse;
    }
//    DELETE
    @DeleteMapping("/product/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id){
        productService.deleteProducts(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Delete Success with id: "+id);
        return apiResponse;
    }
}
