package com.circuitcommerce.project2.dto;

import java.util.List;

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
public class UserOrderDto {
  private String username;
  private List<Long> productIdList;
  private String orderStatus;
  private ChargeRequest chargeRequest;
}
