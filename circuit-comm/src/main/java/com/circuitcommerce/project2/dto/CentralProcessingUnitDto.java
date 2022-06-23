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
public class CentralProcessingUnitDto extends ProductDto {

  private String Series;

  private String l3Cache;

  private String l2Cache;

  private String coolingDevice;

  private String manufacturingTech;

}
