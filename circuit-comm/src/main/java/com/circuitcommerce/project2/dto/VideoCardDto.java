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
public class VideoCardDto extends ProductDto {

  private String coreClock;

  private String maxResolution;

  private String displayPort;

  private String dvi;

  private String hdmi;

  private String cardDimensions;

}
