package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ProductResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import com.poly.ASSIGNMENT_JAVA5.mapper.ProductMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductService {
  final ProductRepository productRepository;
  final ProductMapper productMapper;
  final CloudinaryService cloudinaryService;

  // Get Product
  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream()
        .map(productMapper::toProductResponse)
        .collect(Collectors.toList());
  }

  public Optional<ProductResponse> getProductById(Long id) {
    return productRepository.findById(id).map(productMapper::toProductResponse);
  }

  // Put Product
  public ProductResponse updateProducts(Long id, ProductUpdateRequest request, MultipartFile file) {
    Product product =
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    if (file != null && !file.isEmpty()) {
      cloudinaryService.uploadFile(file);
    }
    productMapper.updateProduct(product, request);
    return productMapper.toProductResponse(productRepository.save(product));
  }

  // Post Product
  public ProductResponse createProducts(ProductCreationRequest request, MultipartFile file) {
    if (file != null && !file.isEmpty()) {
      cloudinaryService.uploadFile(file);
    }
    Product product = productMapper.toProduct(request);
    return productMapper.toProductResponse(productRepository.save(product));
  }

  //    Del Product
  public void deleteProducts(Long id) {
    productRepository.deleteById(id);
  }
}
