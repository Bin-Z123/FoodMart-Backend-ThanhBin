package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.OrderCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.OrderResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.*;
import com.poly.ASSIGNMENT_JAVA5.mapper.OrderMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.payos.PayOS;

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
  PayOS payOS;

  // Lấy order theo user
  public List<Order> getOrderByUser(Long idUser) {
    userRepository
        .findById(idUser)
        .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
    return orderRepository.findAllByUser_Id(idUser);
  }

  // Lấy tất cả Order
  public List<OrderResponse> getAllOrder() {
    return orderRepository.findAll().stream()
        .map(orderMapper::toOrderResponse)
        .collect(Collectors.toList());
  }

  // Tạo đơn hàng :) , HttpServletResponse response
  @Transactional
  public Order createOrder(Long idUser, OrderCreationRequest request) throws Exception {
    // Lấy thông tin người dùng
    User user =
        userRepository
            .findById(idUser)
            .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
    // Lấy giỏ hàng của người dùng
    List<Cart> carts = cartRepository.findAllByUser_Id(idUser);
    if (carts.isEmpty()) {
      throw new RuntimeException("Giỏ hàng trống!");
    }

    Order order = orderMapper.toOrder(request);
    order.setUser(user);
    order.setStatus(Order.OrderStatus.PENDING);
    order.setOrderDate(LocalDateTime.now());
    order.setTotalAmount(request.getTotalAmount());
    Order saveOrder = orderRepository.save(order);

    List<OrderDetail> orderDetails = new ArrayList<>();
    List<Product> updateProduct = new ArrayList<>();
    // Tạo chi tiết đơn hàng
    for (Cart item : carts) {
      Product product = item.getProduct();
      if (product.getStockQuantity() < item.getQuantity()) {
        throw new RuntimeException("Số lượng sản phẩm không đủ trong kho!");
      }
      OrderDetail orderDetail = getOrderDetail(item, saveOrder);
      orderDetails.add(orderDetail);

      // Cập nhật số lượng tồn kho
      product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
      product.setSoldQuantity(product.getSoldQuantity() + item.getQuantity());
      updateProduct.add(product);
    }
    // Lưu dữ liệu
    orderRepository.save(order);
    orderDetailRepository.saveAll(orderDetails);
    productRepository.saveAll(updateProduct);

    // Xoá giỏ hàng sau khi order
    cartRepository.deleteAllByUser(user);
    return saveOrder;
    // tạo link thanh toán
    //        Long orderCode = System.currentTimeMillis()/1000;
    //        List<ItemData> itemDataList = saveOrder.getOrderDetail()
    //                .stream()
    //                .map(i -> ItemData.builder()
    //                        .name(i.getProduct().getNameProduct())
    //                        .quantity(i.getQuantity())
    //                        .price(Integer.parseInt(i.getProduct().getPrice().toString()))
    //                        .build())
    //                .toList();
    //
    //        PaymentData paymentData = PaymentData.builder()
    //                .orderCode(orderCode)
    //                .amount(Integer.parseInt(saveOrder.getTotalAmount().toString()))
    //                .description(saveOrder.getUser().getFullname() + " da dat don hang " +
    // orderCode)
    //                .buyerName(saveOrder.getUser().getFullname())
    //                .buyerEmail(saveOrder.getUser().getEmail())
    //                .buyerAddress(saveOrder.getAddress())
    //                .returnUrl("http://localhost:8080/api/user/order")
    //                .cancelUrl("http://localhost:8080/api/user/order")
    //                .items(itemDataList)
    //                .build();
    //        CheckoutResponseData result = payOS.createPaymentLink(paymentData);
    //        String checkoutUrl = result.getCheckoutUrl();

  }

  private static OrderDetail getOrderDetail(Cart item, Order saveOrder) {
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setOrder(saveOrder);
    orderDetail.setProduct(item.getProduct());
    orderDetail.setPrice(item.getProduct().getPrice());
    orderDetail.setQuantity(item.getQuantity());
    orderDetail.setTotal(
        item.getProduct()
            .getPrice()
            .multiply(BigDecimal.valueOf(item.getQuantity()))
            .setScale(2, RoundingMode.HALF_UP));
    orderDetail.setDescription(item.getProduct().getNameProduct());
    return orderDetail;
  }
}
