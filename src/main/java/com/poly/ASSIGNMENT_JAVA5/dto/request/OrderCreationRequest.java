package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.poly.ASSIGNMENT_JAVA5.entity.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationRequest {
  LocalDateTime orderDate = LocalDateTime.now(); //
  BigDecimal totalAmount;
  String status = "PENDING"; //
  String address;
  //    String updateAt;
  String paymentStatus;
  String description;
  User user;
}
