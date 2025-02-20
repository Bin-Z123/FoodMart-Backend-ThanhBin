package com.poly.ASSIGNMENT_JAVA5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime orderDate = LocalDateTime.now();
    BigDecimal totalAmount;
    Boolean status = true;
    String address;
    String updateAt;
    String paymentStatus;
    String description;
    @ManyToOne
    @JoinColumn(name = "iduser")
    @JsonBackReference
    User user;

}
