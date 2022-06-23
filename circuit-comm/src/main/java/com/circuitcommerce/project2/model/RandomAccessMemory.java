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
public class RandomAccessMemory extends Product {
  @Column(nullable = false)
  private Integer caseLatency;

  @Column(nullable = false)
  private String voltage;

  @Column(nullable = false)
  private String multiChannelKit;

  @Column(nullable = false)
  private String timing;
}
