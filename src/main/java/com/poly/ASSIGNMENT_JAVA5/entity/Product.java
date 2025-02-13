package com.poly.ASSIGNMENT_JAVA5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

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
    String nameProduct;
    BigDecimal price;
    String image;
    boolean status = true;
    BigDecimal discount = new BigDecimal(0);
    int stockQuantity;
    int soldQuantity;
    Date createAt;
    @ManyToOne//fetch = FetchType.EAGERs
    @JoinColumn(name = "id_category")
    @JsonBackReference
    Category category;
}
