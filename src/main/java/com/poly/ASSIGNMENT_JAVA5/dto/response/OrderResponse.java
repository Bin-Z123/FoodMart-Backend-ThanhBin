package com.poly.ASSIGNMENT_JAVA5.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    LocalDateTime orderDate;
    BigDecimal totalAmount;
    Boolean status;
    String address;
    String updateAt;
    String paymentStatus;
    String description;
    Long user_id;
}
