package com.jay.util;

import java.util.Optional;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jay.security.JwtProvider;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.InternalServerErrorResponse;

public class JwtUtil {
  private final static String CONTEXT_ATTRIBUTE = "LOGIN_INFO";
  private final static String COOKIE_KEY = "LOGIN_INFO";

  public static boolean containsJwt(Context context) {
    return context.attribute(CONTEXT_ATTRIBUTE) != null;
  }

  public static Context addDecodedToContext(Context context, DecodedJWT jwt) {
    context.attribute(CONTEXT_ATTRIBUTE, jwt);
    return context;
  }

  public static DecodedJWT getDecodedFromContext(Context context) {
    Object attribute = context.attribute(CONTEXT_ATTRIBUTE);

    if (!DecodedJWT.class.isInstance(attribute)) {
      throw new InternalServerErrorResponse(
          "The context carried invalid object as jwt");
    }
    return (DecodedJWT) attribute;
  }

  public static String extractUsernameFromJwt(Context context) {
    DecodedJWT decodedJWT = getDecodedFromContext(context);
    String username = decodedJWT.getClaim("username").asString();
    return username;
  }

  public static String extractLevelFromJwt(Context context) {
    DecodedJWT decodedJWT = getDecodedFromContext(context);
    String level = decodedJWT.getClaim("level").asString();
    return level;
  }

  public static Optional<String> getTokenFromHeader(Context context) {
    return Optional.ofNullable(context.header("Authorization"))
        .flatMap(header -> {
          String[] split = header.split(" ");
          if (split.length != 2 || !split[0].equals("Bearer")) {
            return Optional.empty();
          }
          return Optional.of(split[1]);
        });
  }

  public static Optional<String> getTokenFromCookie(Context context) {
    return Optional.ofNullable(context.cookie(COOKIE_KEY));
  }

  public static Context addTokenToCookie(Context context, String token) {
    return context.cookie(COOKIE_KEY, token);
  }

  public static Handler createHeaderDecodeHandler(JwtProvider jwtProvider) {
    return context -> getTokenFromHeader(context)
          .flatMap(jwtProvider::validateToken)
          .ifPresent(jwt -> JwtUtil.addDecodedToContext(context, jwt));
  }

  public static Handler createCookieDecodeHandler(JwtProvider jwtProvider) {
    return context -> getTokenFromCookie(context)
          .flatMap(jwtProvider::validateToken)
          .ifPresent(jwt -> JwtUtil.addDecodedToContext(context, jwt));
  }
}
