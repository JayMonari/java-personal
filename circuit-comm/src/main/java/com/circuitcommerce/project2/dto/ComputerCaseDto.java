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
public class ComputerCaseDto extends ProductDto {

  private String motherboardCompatibility;

  private String frontPorts;

  private String powerSupplyMounted;

  private String sidePannelWindow;

  private String fans;

  private String internalDriveBays;

}
