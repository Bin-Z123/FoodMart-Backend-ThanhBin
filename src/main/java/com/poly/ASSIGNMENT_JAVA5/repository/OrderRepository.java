package com.poly.ASSIGNMENT_JAVA5.repository;

import com.poly.ASSIGNMENT_JAVA5.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser_Id(Long idUser);
}
