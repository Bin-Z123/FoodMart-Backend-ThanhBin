package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.CartRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ApiResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Cart;
import com.poly.ASSIGNMENT_JAVA5.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class CartController {
    CartService cartService;
    //GET
//    @GetMapping("/cart/{id}")
//    public ApiResponse<List<Cart>> getAllByUserId(@PathVariable Long id){
//        ApiResponse<List<Cart>> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(cartService.findAllByUser_Id(id));
//        return apiResponse;
//    }
    @GetMapping("/cart")
    public List<Cart> getAll(){
        return cartService.getAll();
    }
    @GetMapping("/user/cart/{id}")
    public List<Cart> getAllByUserId(@PathVariable Long id){
        return cartService.findAllByUser_Id(id);
    }
    //Post
    @PostMapping("/user/cart")
    public Cart addToCart(@RequestBody CartRequest request){
        return cartService.addToCart(request);
    }
    //Put
    @PutMapping("/cart")
    public Cart updateCart(@RequestBody CartRequest request){
        return cartService.updateCart(request);
    }

}
