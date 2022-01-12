package com.circuitcommerce.project2.repository;

import com.circuitcommerce.project2.model.Cart;
import com.circuitcommerce.project2.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  public Cart findByUser(User user);

  public static final String CLEAR_CART = "DELETE FROM product_cart_list WHERE cart_list_cart_id = ?1 ";
  @Modifying
  @Transactional
  @Query(value = CLEAR_CART, nativeQuery = true)
  public void clearCart(long cId);


}
