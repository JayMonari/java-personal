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
public class ComputerCase extends Product {
  @Column(nullable = false)
  private String motherboardCompatibility;

  @Column(nullable = false)
  private String frontPorts;

  @Column(nullable = false)
  private String powerSupplyMounted;

  @Column(nullable = true)
  private String sidePannelWindow;

  @Column(nullable = true)
  private String fans;

  @Column(nullable = true)
  private String internalDriveBays;
}
