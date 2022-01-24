package com.jay.security;

import java.util.Optional;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jay.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtProvider {
  private final Algorithm algorithm;
  private final JwtGenerator<User> generator;
  private final JWTVerifier verifier;

  public String generateToken(User user) {
    return generator.generate(user, algorithm);
  }

  public Optional<DecodedJWT> validateToken(String token) {
    try {
      return Optional.of(verifier.verify(token));
    } catch (JWTVerificationException e) {
      return Optional.empty();
    }
  }
}
