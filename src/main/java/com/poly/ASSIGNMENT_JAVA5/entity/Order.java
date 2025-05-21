package com.poly.ASSIGNMENT_JAVA5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

  @Enumerated(EnumType.STRING)
  OrderStatus status;

  String address;
  String updateAt;

  @Enumerated(EnumType.STRING)
  PaymentStatus paymentStatus;

  String description;

  @ManyToOne
  @JoinColumn(name = "iduser")
  @JsonBackReference
  User user;

  @OneToMany(mappedBy = "order")
  @JsonManagedReference
  List<OrderDetail> orderDetail;

  public enum PaymentStatus {
    COD,
    MOMO,
    BANK
  }

  public enum OrderStatus {
    PENDING,
    CONFIRMED,
    DELIVERED,
    CANCELLED
  }
}
