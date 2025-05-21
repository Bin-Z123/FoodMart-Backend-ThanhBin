package com.poly.ASSIGNMENT_JAVA5.dto.response;

import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import java.util.Date;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
  Long id;
  String name;
  boolean status = true;
  Date createAt;
  String description;
  List<Product> product;
}
