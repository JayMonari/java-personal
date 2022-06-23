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
public class HardDiskDrive extends Product {
  @Column(nullable = false)
  private Double height;

  @Column(nullable = false)
  private Double width;

  @Column(nullable = false)
  private Double length;

  @Column(nullable = true)
  private Double weight;

  @Column(nullable = true)
  private String packageContents;

  @Column(nullable = true)
  private Double averageLatency;
}
