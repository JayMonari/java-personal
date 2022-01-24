package com.jay.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.jay.model.User;
import com.jay.security.JwtGenerator;
import com.jay.security.JwtProvider;

public class BasicJwtProvider {

  public static JwtProvider createHMAC512() {
    Algorithm algorithm = Algorithm.HMAC256(System.getenv("SECRET"));

    JwtGenerator<User> generator = (user, alg) -> {
      JWTCreator.Builder token = JWT.create()
            .withClaim("username", user.getUsername())
            .withClaim("level", user.getUserRole().toString());
      return token.sign(alg);
    };

    JWTVerifier verifier = JWT.require(algorithm).build();

    return new JwtProvider(algorithm, generator, verifier);
  }
}
