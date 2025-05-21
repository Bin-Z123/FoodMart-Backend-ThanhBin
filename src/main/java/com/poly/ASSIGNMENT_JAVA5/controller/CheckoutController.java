package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.CartRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.payos.PayOS;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
public class CheckoutController {
  final PayOS payOS;

  @PostMapping(value = "/create-payment-link", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void checkout(HttpServletResponse response, @RequestBody CartRequest cartRequest) {
    try {
      Long orderCode = System.currentTimeMillis() / 1000;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
