package com.circuitcommerce.project2.dto;

import lombok.Data;

@Data
public class ErrorInfo {
  private final String url;
  private final String exMsg;

  public ErrorInfo(String url, Exception exMsg) {
    this.url = url;
    this.exMsg = exMsg.getLocalizedMessage();
  }
}
