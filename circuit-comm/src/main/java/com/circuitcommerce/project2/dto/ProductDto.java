package com.circuitcommerce.project2.dto;

import com.circuitcommerce.project2.model.Cart;
import com.circuitcommerce.project2.model.Review;
import com.circuitcommerce.project2.model.UserOrder;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductDto {

  private String modelNumber;

  private String title;

  private String abbreviatedTitle;

  private String brand;

  private Double price;

  private List<Cart> carts;

  private List<Review> reviewList;

  private List<UserOrder> orderedBy;
}