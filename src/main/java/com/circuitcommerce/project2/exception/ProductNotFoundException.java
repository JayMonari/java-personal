package com.circuitcommerce.project2.exception;

public class ProductNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ProductNotFoundException(String exMsg) {
    super(exMsg);
  }
}
