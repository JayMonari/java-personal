package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.model.Product;
import com.circuitcommerce.project2.service.CartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/user/cart")
@AllArgsConstructor
public class CartController {
  private final CartService cartService;

  @GetMapping
  public List<Product> getCartByUsername(@RequestHeader("username") String username) {
    return cartService.getCartProducts(username);
  }

  @GetMapping("/add")
  public ResponseEntity<String> addProductToCart(@RequestParam("pid") Long productId,
      @RequestHeader("username") String username) {
    cartService.addProduct(username, productId);
    return new ResponseEntity<>("Product added to cart", HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<Void> removeProductToCart(@RequestParam("pid") Long productId,
      @RequestHeader("username") String username) {
    cartService.removeProduct(username, productId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/clear")
  public ResponseEntity<Void> clearCart(@RequestHeader("username") String username){
    cartService.clearCart(username);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
