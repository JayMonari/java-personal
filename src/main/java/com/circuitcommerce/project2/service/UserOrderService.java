package com.circuitcommerce.project2.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.circuitcommerce.project2.dto.ChargeRequest;
import com.circuitcommerce.project2.dto.UserOrderDto;
import com.circuitcommerce.project2.dto.UserOrderUpdateDto;
import com.circuitcommerce.project2.model.Product;
import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.model.UserOrder;
import com.circuitcommerce.project2.repository.UserOrderRepository;
import com.stripe.exception.StripeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class UserOrderService {
  private UserOrderRepository uoRepo;
  private UserService uServ;
  private ProductService pServ;
  private StripeService stripeService;

  public Optional<List<UserOrder>> findByUser(User user){
    return uoRepo.findByOrderOwner(user);
  }

  public List<UserOrder> getUserOrderHistory(String username){
    User u = uServ.getUser(username).orElseThrow( ()-> new UsernameNotFoundException("When looking for a user, username was not found"));
    List<UserOrder> uo = findByUser(u).orElseThrow( ()-> new UsernameNotFoundException("When looking for orders from user " +username+" no past orders were found"));
    return uo;
  }

  public void updateOrder(UserOrderUpdateDto updateDto){
    uoRepo.changeStatus(updateDto.getOrderId(), updateDto.getOrderStatus());
  }

  public void addOrder(UserOrderDto uOrderDto) throws StripeException {
    String receiptUrl = stripeService.charge(uOrderDto.getChargeRequest());
    User u = uServ.getUser(uOrderDto.getUsername()).orElseThrow( ()-> new UsernameNotFoundException("When looking for a user, username was not found"));
    List<Product> pList = new ArrayList<>();
    for(long i: uOrderDto.getProductIdList()){
      pList.add(pServ.getProductByProductId(i));
    }
    UserOrder uOrder = UserOrder.builder()
      .orderTime(Instant.now())
      .orderStatus("PAID")
      .receiptUrl(receiptUrl)
      .orderOwner(u)
      .build();
    
    uoRepo.save(uOrder);
    Long orderId = uOrder.getOrderId();
    for(long i:uOrderDto.getProductIdList()){
      uoRepo.addProductByOrderId(i, orderId);
    }
  }
}
