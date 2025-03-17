package com.poly.ASSIGNMENT_JAVA5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    Boolean status;
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

    public enum PaymentStatus{
        COD,
        MOMO,
        BANK
    }

}
