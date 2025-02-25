package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
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
