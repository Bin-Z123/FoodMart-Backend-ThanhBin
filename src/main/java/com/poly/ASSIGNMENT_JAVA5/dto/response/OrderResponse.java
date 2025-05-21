package com.poly.ASSIGNMENT_JAVA5.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import com.poly.ASSIGNMENT_JAVA5.entity.OrderDetail;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
