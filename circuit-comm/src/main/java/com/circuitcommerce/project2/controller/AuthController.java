package com.circuitcommerce.project2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.circuitcommerce.project2.dto.AuthenticationResponse;
import com.circuitcommerce.project2.dto.ErrorInfo;
import com.circuitcommerce.project2.dto.LoginRequest;
import com.circuitcommerce.project2.dto.RefreshTokenRequest;
import com.circuitcommerce.project2.dto.RegisterRequest;
import com.circuitcommerce.project2.dto.ResetPasswordRequest;
import com.circuitcommerce.project2.dto.UpdatePasswordRequest;
import com.circuitcommerce.project2.exception.TokenHasExpiredException;
import com.circuitcommerce.project2.service.AuthService;
import com.circuitcommerce.project2.service.RefreshTokenService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public final class AuthController {
  private final AuthService authService;
  private final RefreshTokenService refreshTokenService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody final RegisterRequest registerRequest) {
    authService.signup(registerRequest);
    return new ResponseEntity<>("User Registration Succesful!", HttpStatus.OK);
  }

  @GetMapping("/account-verification/{token}")
  public ResponseEntity<String> verifyAccount(@PathVariable final String token) {
    authService.verifyAccount(token);
    return new ResponseEntity<>("Account Activated!", HttpStatus.OK);
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody final LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @PostMapping("/refresh/token")
  public AuthenticationResponse refreshToken(@Valid @RequestBody final RefreshTokenRequest refreshTokenRequest) {
    return authService.refreshToken(refreshTokenRequest);
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(@Valid @RequestBody final RefreshTokenRequest refreshTokenRequest) {
    final String refreshToken = refreshTokenRequest.getRefreshToken();
    refreshTokenService.deleteRefreshToken(refreshToken);
    return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!");
  }

  @PostMapping("/reset-password")
  public ResponseEntity<Void> resetPassword(@Valid @RequestBody final ResetPasswordRequest resetPasswordRequest) {
    authService.resetPassword(resetPasswordRequest);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/account-reset-password/{token}")
  public RedirectView verifyPasswordToken(@PathVariable final String token) {
    String username = authService.verifyPasswordToken(token).getUser().getUsername();

    return new RedirectView("http://circuit-commerce-frontend.s3-website-us-east-1.amazonaws.com/user/" + username + "/update-password/" + token);
  }

  @PostMapping("/account-update-password")
  public ResponseEntity<Void> updatePassword(@Valid @RequestBody final UpdatePasswordRequest updatePasswordRequest) {
    authService.updatePassword(updatePasswordRequest);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(TokenHasExpiredException.class)
  @ResponseBody
  public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
    return new ErrorInfo(req.getRequestURL().toString(), ex);
  }
}
