package com.jay.controller;

import com.jay.dto.LoginRequest;
import com.jay.exception.NoSuchUserException;
import com.jay.model.User;
import com.jay.model.UserRole;
import com.jay.security.JwtProvider;
import com.jay.service.AuthService;
import com.jay.util.JavalinUtil;
import com.jay.util.JwtUtil;

import io.javalin.http.Context;

public class AuthController {
  private static AuthService authService;
  private static JwtProvider jwtProvider;

  public AuthController(AuthService authService, JwtProvider jwtProvider) {
    AuthController.authService = authService;
    AuthController.jwtProvider = jwtProvider;
  }

  public static void login(Context context) {
    try {
      LoginRequest loginRequest = context.bodyAsClass(LoginRequest.class);
      User user = authService.login(loginRequest);
      String token = jwtProvider.generateToken(user);
      JwtUtil.addTokenToCookie(context, token);

      if (user.getUserRole() == UserRole.USER) {
        context.redirect(JavalinUtil.EMPLOYEE);
      } else {
        context.redirect(JavalinUtil.FINANCE_MANAGER);
      }
    } catch (NoSuchUserException e) {
      context.status(401);
    }
  }

  public static void logout(Context context) {
    context.removeCookie("LOGIN_INFO").redirect(JavalinUtil.LOGIN, 301);
    ;
  }
}

