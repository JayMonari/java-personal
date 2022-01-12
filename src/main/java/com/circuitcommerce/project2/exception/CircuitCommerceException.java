package com.circuitcommerce.project2.exception;

public class CircuitCommerceException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public CircuitCommerceException() {
    super();
  }

  public CircuitCommerceException(String exceptionMessage) {
    super(exceptionMessage);
  }
}
