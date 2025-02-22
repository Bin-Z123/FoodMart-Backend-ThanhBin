package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.CartRequest;
import com.poly.ASSIGNMENT_JAVA5.entity.Cart;
import com.poly.ASSIGNMENT_JAVA5.entity.CartId;
import com.poly.ASSIGNMENT_JAVA5.entity.Product;
import com.poly.ASSIGNMENT_JAVA5.entity.User;
import com.poly.ASSIGNMENT_JAVA5.repository.CartRepository;
import com.poly.ASSIGNMENT_JAVA5.repository.ProductRepository;
import com.poly.ASSIGNMENT_JAVA5.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepository cartRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    //get
    public List<Cart> getAll() {
       return cartRepository.findAll();
    }
    public List<Cart> findAllByUser_Id(Long id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findAllByUser_Id(id);
    }
    public List<Cart> findAllByUser(User user) {
        return cartRepository.findAllByUser(user);
    }
    public Optional<Cart> findByUserAndProduct(User user, Product product) {
        return cartRepository.findByUserAndProduct(user, product);
    }
    //post
    public Cart addToCart(CartRequest request){
        CartId cartId = new CartId(request.getIdUser(), request.getIdProduct());
        User user = userRepository.findById(request.getIdUser()).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(request.getIdProduct()).orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<Cart> existCart = cartRepository.findById(cartId);
        Cart cart;
        if (existCart.isPresent()){
            cart = existCart.get();
            cart.setQuantity(cart.getQuantity() + request.getQuantity());
        }else {
            cart = new Cart();
            cart.setId(cartId);
            cart.setQuantity(request.getQuantity());
            cart.setUser(user);
            cart.setProduct(product);
        }
        return cartRepository.save(cart);
    }
    //put
    public Cart updateCart(CartRequest request){
        CartId cartId = new CartId(request.getIdUser(), request.getIdProduct());
        Optional<Cart> existCart = cartRepository.findById(cartId);
        Cart cart;
        if (existCart.isPresent()){
            cart = existCart.get();
            cart.setQuantity(request.getQuantity());
        }else {
            throw new RuntimeException("Cart not found");
        }
        return cartRepository.save(cart);
    }
}
