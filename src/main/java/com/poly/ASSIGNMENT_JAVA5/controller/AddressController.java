package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.AddressCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.AddressResponse;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ApiResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Address;
import com.poly.ASSIGNMENT_JAVA5.service.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api")
public class AddressController {
    AddressService addressService;

    @GetMapping("/user/address/{id}")
    public ApiResponse<List<Address>> getAddressByUserId(@PathVariable Long id){
        ApiResponse<List<Address>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(addressService.getAllByUserId(id));
        return apiResponse;
    }

    @PostMapping("/user/address")
    public ApiResponse<AddressResponse> createAddress(@RequestBody AddressCreationRequest request){
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(addressService.create(request));
        return apiResponse;
    }
}
