package com.poly.ASSIGNMENT_JAVA5.dto.request;


import com.poly.ASSIGNMENT_JAVA5.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressCreationRequest {
    String city;
    String district;
    String street;
    String description;
    int phone;
    User user;
}
