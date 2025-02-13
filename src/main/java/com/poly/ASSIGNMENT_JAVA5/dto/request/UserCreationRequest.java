package com.poly.ASSIGNMENT_JAVA5.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 6, max = 18)
    String username;
    @Size(min = 6, max = 18)
    String password;
    String email;
    String fullname;
    Boolean role = true;
}
