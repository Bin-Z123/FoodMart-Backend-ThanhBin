package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.OrderCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.OrderResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.*;
import com.poly.ASSIGNMENT_JAVA5.mapper.OrderMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    CartRepository cartRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;
    OrderMapper orderMapper;

    //Lấy order theo user
    public List<Order> getOrderByUser(Long idUser){
        userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        return  orderRepository.findAllByUser_Id(idUser);
    }

    //Tạo đơn hàng :)
    @Transactional
    public Order createOrder(Long idUser, OrderCreationRequest request){
        //Lấy thông tin người dùng
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        // Lấy giỏ hàng của người dùng
        List<Cart> carts = cartRepository.findAllByUser_Id(idUser);
        if (carts.isEmpty()){
            throw new RuntimeException("Giỏ hàng trống!");
        }

        Order order = orderMapper.toOrder(request);
        order.setUser(user);
        order.setStatus(false);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(request.getTotalAmount());
        Order saveOrder = orderRepository.save(order);

        List<OrderDetail> orderDetails = new  ArrayList<>();
        List<Product> updateProduct = new ArrayList<>();

        // Tạo chi tiết đơn hàng
        for (Cart item: carts){
            Product product = item.getProduct();
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Số lượng sản phẩm không đủ trong kho!");
            }
            OrderDetail orderDetail = getOrderDetail(item, saveOrder);
            orderDetails.add(orderDetail);

            //Cập nhật số lượng tồn kho
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            product.setSoldQuantity(product.getSoldQuantity() + item.getQuantity());
            updateProduct.add(product);
        }
        //Lưu dữ liệu
        orderRepository.save(order);
        orderDetailRepository.saveAll(orderDetails);
        productRepository.saveAll(updateProduct);

        //Xoá giỏ hàng sau khi order
        cartRepository.deleteAllByUser(user);
        return saveOrder;
    }

    private static OrderDetail getOrderDetail(Cart item, Order saveOrder) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(saveOrder);
        orderDetail.setProduct(item.getProduct());
        orderDetail.setPrice(item.getProduct().getPrice());
        orderDetail.setQuantity(item.getQuantity());
        orderDetail.setTotal(item.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()))
                .setScale(2, RoundingMode.HALF_UP));
        orderDetail.setDescription(item.getProduct().getNameProduct());
        return orderDetail;
    }
}
