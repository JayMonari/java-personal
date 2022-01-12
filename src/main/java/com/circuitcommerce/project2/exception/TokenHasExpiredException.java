package com.circuitcommerce.project2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The provided token has expired")
public class TokenHasExpiredException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TokenHasExpiredException() {
    super();
  }

  public TokenHasExpiredException(String exMsg) {
    super(exMsg);
  }
}
