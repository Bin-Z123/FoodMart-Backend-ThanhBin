package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.poly.ASSIGNMENT_JAVA5.entity.Category;
import java.math.BigDecimal;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateRequest {
  String nameProduct;
  BigDecimal price;
  String image;
  boolean status = true;
  BigDecimal discount;
  String description;
  Category category;
  Long category_id;
  String stockQuantity;
}
