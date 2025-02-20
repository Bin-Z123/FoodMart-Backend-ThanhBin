package com.poly.ASSIGNMENT_JAVA5.mapper;

import com.poly.ASSIGNMENT_JAVA5.dto.request.CategoryCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.CategoryUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.CategoryResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CategoryCreationRequest request);
    void updateCategory(@MappingTarget Category category, CategoryUpdateRequest request);
}
