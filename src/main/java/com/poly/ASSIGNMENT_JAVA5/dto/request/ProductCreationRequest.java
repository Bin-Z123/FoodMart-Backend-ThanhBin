package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.poly.ASSIGNMENT_JAVA5.entity.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreationRequest {
    @NotBlank(message = "Name product is required")
    String nameProduct;
    @Min(value = 0,message = "Price must be greater than 0")
    @NotNull(message = "Price is required")
    BigDecimal price;
    @NotBlank(message = "Image is required")
    String image;
    BigDecimal discount ;
    @NotNull(message = "Stock quantity is required")
    @Min(value = 1,message = "Stock quantity must be greater than 1")
    int stockQuantity;
    String description;
    @NotNull(message = "Category is required")
    Category category;
}
