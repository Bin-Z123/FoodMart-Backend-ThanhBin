package com.poly.ASSIGNMENT_JAVA5.controller;

import com.poly.ASSIGNMENT_JAVA5.dto.request.UserCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.UserUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ApiResponse;
import com.poly.ASSIGNMENT_JAVA5.dto.response.UserResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import com.poly.ASSIGNMENT_JAVA5.mapper.UserMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.UserRepository;
import com.poly.ASSIGNMENT_JAVA5.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    final UserService userService;
    final UserRepository userRepository;
    final UserMapper userMapper;

//    Get
    @GetMapping("/user")
    public List<UserResponse> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public Optional<UserResponse> getUserByID(@PathVariable Long id){
        return userService.getUserByIDs(id);
    }



    @GetMapping("/user/profile")
    public ResponseEntity<ApiResponse<UserResponse>> getUserProfile(Authentication authentication){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        if (authentication == null || !authentication.isAuthenticated()){
            apiResponse.setResult(null);
            apiResponse.setMessage("Người dùng chưa đăng nhập");
            System.out.println(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        }
        // Lấy thông tin username từ authentication
        String username = authentication.getName();
        // Tìm thông tin người dùng trong DB
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User name not found!!!"));
        apiResponse.setResult(userMapper.toUserResponse(user));
        return ResponseEntity.ok(apiResponse);
    }



//    Put
    @PutMapping("/user/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id,@RequestBody UserUpdateRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUsers(id,request));
        return apiResponse;
    }
//    Post
    @PostMapping("/register/user")
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
