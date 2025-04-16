package com.poly.ASSIGNMENT_JAVA5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Name product is required")
    String nameProduct;
    @NotNull(message = "Price product is required")
    @PositiveOrZero(message = "Price must be greater than 0")
    BigDecimal price;
    String image;
    boolean status = true;
    BigDecimal discount;
    int stockQuantity;
    int soldQuantity;
    LocalDateTime createAt = LocalDateTime.now();
    String description;
    @ManyToOne//fetch = FetchType.EAGERs
    @JoinColumn(name = "id_category")
    @JsonBackReference
    Category category;
}
