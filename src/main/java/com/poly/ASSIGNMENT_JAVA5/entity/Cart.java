package com.poly.ASSIGNMENT_JAVA5.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Entity
@Table(name = "Cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @EmbeddedId
    CartId id;
    int quantity;
    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    User user;
    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_product")
    Product product;

}
