package com.jay.exception;

public class InvalidPasswordException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public InvalidPasswordException() {
    super();
  }

  public InvalidPasswordException(String exMsg) {
    super(exMsg);
  }
}
