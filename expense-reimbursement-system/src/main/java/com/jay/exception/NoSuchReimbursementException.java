package com.jay.exception;

public class NoSuchReimbursementException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NoSuchReimbursementException(String exMessage) {
    super(exMessage);
  }
}
