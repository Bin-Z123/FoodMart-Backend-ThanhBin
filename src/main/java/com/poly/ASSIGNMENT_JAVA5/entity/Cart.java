package com.poly.ASSIGNMENT_JAVA5.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "Cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    int quantity;
    @ManyToOne
    @JoinColumn(name = "id_product")
    Product product;
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user;
}
