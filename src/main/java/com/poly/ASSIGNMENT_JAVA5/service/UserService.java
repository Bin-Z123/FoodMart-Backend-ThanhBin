package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.UserCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.UserUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.UserResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import com.poly.ASSIGNMENT_JAVA5.mapper.UserMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    //Get user
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }
    public Optional<UserResponse> getUserByIDs(Long id){
        return  userRepository.findById(id).map(userMapper::toUserResponse);
    }
    //Post User
    public UserResponse createUsers(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username exists");
        }
        User user = new User();
        user = userMapper.toUser(request);
        return userMapper.toUserResponse(userRepository.save(user));
    }
//    Put User
    public UserResponse updateUsers(Long id ,UserUpdateRequest request){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }
//    Del User
    public void deleteUsers(Long id){
        userRepository.deleteById(id);
    }
}
