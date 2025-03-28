package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationRequest {
    LocalDateTime orderDate = LocalDateTime.now();//
    BigDecimal totalAmount;
    Boolean status = false;//
    String address;
//    String updateAt;
    String paymentStatus;
    String description;
    User user;
}
