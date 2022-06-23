package com.circuitcommerce.project2.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductShortDto {
  private Long productId;
  private String abbreviatedTitle;
  private String brand;
  private Double price;
  private String modelNumber;
}
