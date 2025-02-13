package com.poly.ASSIGNMENT_JAVA5.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String fullname;
    @Size(min = 6, max = 18)
    String password;
    Boolean role = true;
    String avatar = "avt";
}
