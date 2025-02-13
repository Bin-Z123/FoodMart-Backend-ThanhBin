package com.poly.ASSIGNMENT_JAVA5.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryUpdateRequest {
    String name;
    boolean status = true;
    Date createAt;
    String description;
}
