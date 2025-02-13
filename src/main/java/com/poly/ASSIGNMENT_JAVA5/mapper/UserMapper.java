package com.poly.ASSIGNMENT_JAVA5.mapper;

import com.poly.ASSIGNMENT_JAVA5.dto.request.UserCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.UserUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.UserResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
