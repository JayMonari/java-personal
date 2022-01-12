package com.circuitcommerce.project2.repository;

import com.circuitcommerce.project2.model.UserOrder;
import com.circuitcommerce.project2.dto.UserOrderDto;
import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.model.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
  public Optional<List<UserOrder>> findByOrderOwner(User user);

  public static final String INSERT_PRODUCT_BY_USER = "INSERT INTO product_ordered_by(product_list_product_id, ordered_by_order_id) VALUES (?1,?2)";
  @Modifying
  @Transactional
  @Query(value = INSERT_PRODUCT_BY_USER, nativeQuery = true)
  public void addProductByOrderId(Long pId, Long oId);

  public static final String CHANGE_STATUS = "UPDATE UserOrder uo set uo.orderStatus = ?2 WHERE uo.orderId = ?1";
  @Modifying
  @Query(value = CHANGE_STATUS)
  @Transactional
  public void changeStatus(long oId, String status);
}
