package com.poly.ASSIGNMENT_JAVA5.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {
  Long id;
  String oldPassword;
  String newPassword;
  String confirmPassword;
}
