package com.poly.ASSIGNMENT_JAVA5.repository;

import com.poly.ASSIGNMENT_JAVA5.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
