package com.poly.ASSIGNMENT_JAVA5.mapper;

import com.poly.ASSIGNMENT_JAVA5.dto.request.UserCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.UserUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.UserResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(target = "password", source = "password", qualifiedByName = "passwordEncoder")
  User toUser(UserCreationRequest request, @Context PasswordEncoder passwordEncoder);

  UserResponse toUserResponse(User user);

  @Mapping(target = "password", source = "password", qualifiedByName = "passwordEncoder")
  void updateUser(
      @MappingTarget User user,
      UserUpdateRequest request,
      @Context PasswordEncoder passwordEncoder);

  @Named("passwordEncoder")
  default String passwordEncoder(String password, @Context PasswordEncoder passwordEncoder) {
    return passwordEncoder.encode(password);
  }
}
