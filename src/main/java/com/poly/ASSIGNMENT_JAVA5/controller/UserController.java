package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.UserCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.UserUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ApiResponse;
import com.poly.ASSIGNMENT_JAVA5.dto.response.UserResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import com.poly.ASSIGNMENT_JAVA5.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    final UserService userService;

//    Get
        @GetMapping("/user")
    public List<UserResponse> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public Optional<UserResponse> getUserByID(@PathVariable Long id){
        return userService.getUserByIDs(id);
    }
//    Put
    @PutMapping("/user/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id,@RequestBody UserUpdateRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUsers(id,request));
        return apiResponse;
    }
//    Post
    @PostMapping("/user")
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUsers(request));
        return apiResponse;
    }
//    Delete
    @DeleteMapping("/user/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id){
        userService.deleteUsers(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Delete Success User with id: "+id);
        return apiResponse;
    }
}
