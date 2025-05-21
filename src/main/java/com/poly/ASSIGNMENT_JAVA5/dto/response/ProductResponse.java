package com.poly.ASSIGNMENT_JAVA5.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
  Long id;
  String nameProduct;
  BigDecimal price;
  String image;
  boolean status = true;
  BigDecimal discount = new BigDecimal(0);
  int stockQuantity;
  int soldQuantity;
  String description;
  Date createAt;
  Long category_id;
  String category_name;
}
