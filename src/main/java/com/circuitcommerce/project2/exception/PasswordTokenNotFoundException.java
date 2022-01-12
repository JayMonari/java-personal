package com.circuitcommerce.project2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The provided token could not be found")
public class PasswordTokenNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public PasswordTokenNotFoundException() {
    super();
  }

  public PasswordTokenNotFoundException(String exMsg) {
    super(exMsg);
  }
}
