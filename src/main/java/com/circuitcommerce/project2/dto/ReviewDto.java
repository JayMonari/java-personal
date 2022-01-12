package com.circuitcommerce.project2.dto;


import java.time.Instant;

import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

  private Double starRating;

  private String comments;

  private String username;

  private Long productId;
}
