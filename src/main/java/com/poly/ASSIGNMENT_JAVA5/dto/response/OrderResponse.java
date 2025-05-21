package com.poly.ASSIGNMENT_JAVA5.dto.response;

import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import com.poly.ASSIGNMENT_JAVA5.entity.OrderDetail;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
  Long id;
  LocalDateTime orderDate;
  BigDecimal totalAmount;
  Order.OrderStatus status;
  String address;
  String updateAt;
  String paymentStatus;
  String description;
  Long user_id;
  String user_fullname;
  List<OrderDetail> orderDetail;
}
