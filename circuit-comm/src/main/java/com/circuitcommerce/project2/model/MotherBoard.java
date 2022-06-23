package com.circuitcommerce.project2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "productId")
public class MotherBoard extends Product {

  @Column(nullable = false)
  @Lob
  private String memoryStandard;

  @Column(nullable = false)
  private String numOfMemorySlots;

  @Column(nullable = true)
  private String audioChipset;

  @Column(nullable = true)
  private String onboardVideoChipset;

  @Column(nullable = true)
  @Lob
  private String pciExpress;
}
