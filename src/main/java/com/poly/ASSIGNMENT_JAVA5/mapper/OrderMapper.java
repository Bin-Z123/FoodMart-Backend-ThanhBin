package com.poly.ASSIGNMENT_JAVA5.mapper;

import com.poly.ASSIGNMENT_JAVA5.dto.request.OrderCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.OrderUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.OrderResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "user_id", source = "user.id")
  @Mapping(target = "user_fullname", source = "user.fullname")
  OrderResponse toOrderResponse(Order order);

  Order toOrder(OrderCreationRequest request);

  void updateOrder(@MappingTarget Order order, OrderUpdateRequest request);
}
