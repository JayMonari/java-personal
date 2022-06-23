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
public class MotherBoardDto extends ProductDto {

  private String memoryStandard;

  private String NumOfMemorySlots;

  private String AudioChipset;

  private String onboardVideoChipset;

  private String pciExpress;

}
