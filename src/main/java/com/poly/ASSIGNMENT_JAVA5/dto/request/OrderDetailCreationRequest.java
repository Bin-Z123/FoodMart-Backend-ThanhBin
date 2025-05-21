package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import java.math.BigDecimal;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailCreationRequest {
  int quantity;
  BigDecimal price;
  BigDecimal total;
  String description;
  Order order;
  Product product;
}
