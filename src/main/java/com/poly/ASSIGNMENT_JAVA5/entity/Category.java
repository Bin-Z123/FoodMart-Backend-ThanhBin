package com.poly.ASSIGNMENT_JAVA5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Categories")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    boolean status = true;
    LocalDateTime createAt = LocalDateTime.now();
    String description;
    @OneToMany(mappedBy = "category")//,fetch = FetchType.LAZY
    @JsonManagedReference
    List<Product> product;
}
