package com.jay.dto;

import java.time.Instant;

import com.jay.model.ReimbursementStatus;
import com.jay.model.ReimbursementType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReimbursementDto {
  private Integer reimbursementId;
  private Double amount;
  private Instant submittedAt;
  private Instant resolvedAt;
  private String description;
  private byte[] receipt;
  private String author;
  private String resolver;
  private ReimbursementStatus status;
  private ReimbursementType type;
}
