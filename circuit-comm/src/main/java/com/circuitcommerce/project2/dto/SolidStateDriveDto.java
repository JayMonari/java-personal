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
public class SolidStateDriveDto extends ProductDto {

  private String maxSeqRead;

  private String maxSeqWrite;

  private String usedFor;

  private String mttf;

  private String kb4RandomRead;

  private String kb4RandomWrite;

  private String controller;
}
