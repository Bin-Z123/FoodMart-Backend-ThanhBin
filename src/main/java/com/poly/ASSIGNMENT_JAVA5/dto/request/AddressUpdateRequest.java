package com.poly.ASSIGNMENT_JAVA5.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressUpdateRequest {
  String city;
  String district;
  String street;
  String description;
  int phone;
}
