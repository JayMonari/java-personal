package com.jay.security;

import com.auth0.jwt.algorithms.Algorithm;

@FunctionalInterface
public interface JwtGenerator <T> {
  public String generate(T obj, Algorithm algorithm);
}
