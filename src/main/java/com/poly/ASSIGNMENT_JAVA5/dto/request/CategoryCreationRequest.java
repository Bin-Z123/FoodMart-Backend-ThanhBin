package com.poly.ASSIGNMENT_JAVA5.dto.request;

import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryCreationRequest {
  String name;
  boolean status = true;
  LocalDateTime createAt = LocalDateTime.now();
  String description;
}
