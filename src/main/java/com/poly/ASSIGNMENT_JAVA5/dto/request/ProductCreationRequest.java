package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.poly.ASSIGNMENT_JAVA5.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreationRequest {
    String nameProduct;
    BigDecimal price;
    String image;
    BigDecimal discount ;
    int stockQuantity;
    String description;
    Category category;
}
