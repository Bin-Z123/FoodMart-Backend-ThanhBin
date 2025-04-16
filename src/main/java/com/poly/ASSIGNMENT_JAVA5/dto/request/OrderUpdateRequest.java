package com.poly.ASSIGNMENT_JAVA5.dto.request;

import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
    String status;
}
