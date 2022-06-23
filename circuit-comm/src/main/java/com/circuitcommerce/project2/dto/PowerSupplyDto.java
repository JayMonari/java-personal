package com.circuitcommerce.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PowerSupplyDto extends ProductDto {

  private String fans;

  private String mainConnector;

  private String rails;

  private String pciExpressConnector;
}
