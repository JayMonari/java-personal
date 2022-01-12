package com.circuitcommerce.project2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class CentralProcessingUnit extends Product {
  @Column(nullable = false)
  private String series;

  @Column(nullable = false)
  private String l3Cache;

  @Column(nullable = true)
  private String l2Cache;

  @Column(nullable = false)
  private String coolingDevice;

  @Column(nullable = true)
  private String manufacturingTech;
}
