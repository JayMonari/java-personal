package com.circuitcommerce.project2.service;

import java.util.ArrayList;
import java.util.List;

import com.circuitcommerce.project2.exception.ProductNotFoundException;
import com.circuitcommerce.project2.model.Cart;
import com.circuitcommerce.project2.model.Product;
import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.repository.CartRepository;
import com.circuitcommerce.project2.repository.ProductRepository;
import com.circuitcommerce.project2.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
  // private final AuthService authService;
  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public List<Product> getCartProducts(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Could not find username: " + username));
    return user.getCart().getCartProducts();
  }

  public void addProduct(String username, Long productId) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
              "User not found adding Product to cart"));

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(
      "Product with id: " + productId + "not found, in CartService"
    ));

    List<Cart> carts = product.getCartList();
    Cart cart = user.getCart();
    carts.add(cart);
    productRepository.save(product);
  }

  public void removeProduct(String username, Long productId) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
              "User not found adding Product to cart"));

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(
      "Product with id: " + productId + "not found, in CartService"
    ));
    List<Cart> carts = product.getCartList();
    Cart cart = user.getCart();
    carts.remove(cart);
    productRepository.save(product);
  }

  public void clearCart(String username) {
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException(
        "User not found adding Product to cart"));
    Cart cart = cartRepository.findByUser(user);
    cartRepository.clearCart(cart.getCartId());
  }
}
