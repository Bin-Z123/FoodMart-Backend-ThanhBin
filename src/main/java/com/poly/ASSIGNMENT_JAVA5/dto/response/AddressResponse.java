package com.poly.ASSIGNMENT_JAVA5.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
    String id;
    String city;
    String district;
    String street;
    String description;
    int phone;
    Long user_id;
    String user_fullname;
}
