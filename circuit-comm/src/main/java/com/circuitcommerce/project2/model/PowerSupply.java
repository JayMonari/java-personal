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
public class PowerSupply extends Product {
  @Column(nullable = false)
  private String fans;

  @Column(nullable = false)
  private String mainConnector;

  @Column(nullable = false)
  private String rails;

  @Column(nullable = false)
  private String pciExpressConnector;
}
