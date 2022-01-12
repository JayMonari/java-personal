package com.circuitcommerce.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRequest {

  public enum Currency {
    EUR, USD;
  }
  private String description;
  private Integer amount;
  private Currency currency;
  private String email;
  private String stripeToken;
}
