package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ProductResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import com.poly.ASSIGNMENT_JAVA5.mapper.ProductMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    //Get Product
    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    public Optional<ProductResponse> getProductById(Long id){
        return productRepository.findById(id).map(productMapper::toProductResponse);
    }
    //Put Product
    public ProductResponse updateProducts(Long id, ProductUpdateRequest request){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productMapper.updateProduct(product, request);
        return productMapper.toProductResponse(productRepository.save(product));
    }
    //Post Product
    public ProductResponse createProducts(ProductCreationRequest request){
        Product product = productMapper.toProductRequest(request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

}
