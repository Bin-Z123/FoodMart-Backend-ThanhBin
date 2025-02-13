package com.poly.ASSIGNMENT_JAVA5.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    Long id;
    String name;
    boolean status = true;
    Date createAt;
    String description;
//    @OneToMany(mappedBy = "category")
//    @JsonManagedReference
    List<Product> product;
}
