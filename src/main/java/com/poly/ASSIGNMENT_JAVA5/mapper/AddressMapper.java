package com.poly.ASSIGNMENT_JAVA5.mapper;

import com.poly.ASSIGNMENT_JAVA5.dto.request.AddressCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.AddressUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.AddressResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
  AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

  @Mapping(target = "user_id", source = "user.id")
  @Mapping(target = "user_fullname", source = "user.fullname")
  AddressResponse toAddressResponse(Address address);

  Address toAddress(AddressCreationRequest request);

  void updateAddress(@MappingTarget Address address, AddressUpdateRequest request);
}
