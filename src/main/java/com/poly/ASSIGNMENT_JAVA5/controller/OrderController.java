package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.OrderCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ApiResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import com.poly.ASSIGNMENT_JAVA5.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class OrderController {
    OrderService orderService;
    @GetMapping("/user/order/{idUser}")
    public ApiResponse<List<Order>> getOrderByUser(@PathVariable Long idUser){
        ApiResponse<List<Order>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(orderService.getOrderByUser(idUser));
        return apiResponse;
    }
    @PostMapping("/user/order")
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestParam Long idUser,
                                                          @RequestPart("order") OrderCreationRequest request){
        ApiResponse<Order> apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResult(orderService.createOrder(idUser,request));
            apiResponse.setMessage("Tạo đơn hàng thành công");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setMessage("Tạo đơn hàng thất bại: "+e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
}
