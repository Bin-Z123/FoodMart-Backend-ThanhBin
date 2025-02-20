package com.poly.ASSIGNMENT_JAVA5.mapper;

import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ProductResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "category_id", source = "category.id")
    @Mapping(target = "category_name", source = "category.name")
    ProductResponse toProductResponse(Product product);

    Product toProduct(ProductCreationRequest request);
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
