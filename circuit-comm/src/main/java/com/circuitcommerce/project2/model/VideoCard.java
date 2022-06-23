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
public class VideoCard extends Product {
  @Column(nullable = true)
  private String coreClock;

  @Column(nullable = true)
  private String maxResolution;

  @Column(nullable = true)
  private String displayPort;

  @Column(nullable = true)
  private String dvi;

  @Column(nullable = true)
  private String hdmi;

  @Column(nullable = true)
  private String cardDimensions;
}
