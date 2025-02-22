package com.poly.ASSIGNMENT_JAVA5.repository;

import com.poly.ASSIGNMENT_JAVA5.entity.Cart;
import com.poly.ASSIGNMENT_JAVA5.entity.CartId;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {
    List<Cart> findAllByUser_Id(Long id);
    List<Cart> findAllByUser(User user);
    Optional<Cart> findByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);
    void deleteAllByUser(User user);
}
