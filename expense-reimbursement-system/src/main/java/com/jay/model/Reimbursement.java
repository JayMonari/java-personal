package com.jay.model;

import java.time.Instant;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reimbursement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer reimbursementId;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false, updatable = false)
  private Instant submittedAt;

  private Instant resolvedAt;

  @Column(nullable = false)
  private String description;

  @Lob
  private byte[] receipt;

  @ManyToOne(fetch = FetchType.EAGER)
  private User author;

  @ManyToOne(fetch = FetchType.EAGER)
  private User resolver;

  @Enumerated(EnumType.STRING)
  private ReimbursementStatus statusId;

  @Enumerated(EnumType.STRING)
  private ReimbursementType typeId;

  @Override
  public String toString() {
    return "Reimbursement [amount=" + amount + ", author=" + "//TODO" + ", description=" + description + ", receipt=" + Arrays.toString(receipt)
        +  ", reimbursementId=" + reimbursementId + ", resolvedAt=" + resolvedAt
        + ", resolver=" +  ", statusId=" + statusId + ", submittedAt=" + submittedAt + ", typeId=" + typeId
        + "]";
  }
}
