package com.poly.ASSIGNMENT_JAVA5.mapper;

import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.ProductUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.ProductResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Category;
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
    @Mapping(target = "image", source = "image")
    ProductResponse toProductResponse(Product product);


    Product toProduct(ProductCreationRequest request);
    @Mapping(target = "category", expression = "java(mapCategory(request.getCategory_id()))")
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);

    default Category mapCategory(Long id){
        if (id == null){
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
