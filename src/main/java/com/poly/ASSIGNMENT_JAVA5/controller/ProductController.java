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
import org.springframework.web.multipart.MultipartFile;

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
    @PutMapping("/admin/product/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request, @RequestPart(value = "file", required = false) MultipartFile file){
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        if (file != null && !file.isEmpty()) {
            apiResponse.setResult(productService.updateProducts(id, request, file));
        } else {
            apiResponse.setResult(productService.updateProducts(id, request, null)); // Không có file
        }

        return apiResponse;
    }

//    POST
    @PostMapping("/admin/product")
    public ApiResponse<ProductResponse> createProduct(@RequestPart(value = "product") ProductCreationRequest request, @RequestPart(value = "file", required = false)MultipartFile file){
        System.out.println("Dữ liệu nhân được: "+ request);
        System.out.println("Ảnh: " + request.getImage());
        System.out.println("File: "+file);
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        if (file != null && !file.isEmpty()) {
            apiResponse.setResult(productService.createProducts(request, file));
        } else {
            apiResponse.setResult(productService.createProducts(request, null)); // Không có file
        }
        return apiResponse;
    }
//    DELETE
    @DeleteMapping("/admin/product/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id){
        productService.deleteProducts(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Delete Success with id: "+id);
        return apiResponse;
    }
}
