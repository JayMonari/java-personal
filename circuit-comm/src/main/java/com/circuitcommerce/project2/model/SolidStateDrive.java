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
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "productId")
public class SolidStateDrive extends Product {
  @Column(nullable = false)
  private String maxSeqRead;

  @Column(nullable = false)
  private String maxSeqWrite;

  @Column(nullable = true)
  private String usedFor;

  @Column(nullable = true)
  private String mttf;

  @Column(nullable = true)
  private String kb4RandomRead;

  @Column(nullable = true)
  private String kb4RandomWrite;

  @Column(nullable = true)
  private String controller;
}
