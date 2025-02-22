package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poly.ASSIGNMENT_JAVA5.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
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
    BigDecimal discount ;
    String description;
    Category category;
    Long category_id;
}
