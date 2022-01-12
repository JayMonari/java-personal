package com.circuitcommerce.project2.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.circuitcommerce.project2.dto.AuthenticationResponse;
import com.circuitcommerce.project2.dto.LoginRequest;
import com.circuitcommerce.project2.dto.RefreshTokenRequest;
import com.circuitcommerce.project2.dto.RegisterRequest;
import com.circuitcommerce.project2.dto.ResetPasswordRequest;
import com.circuitcommerce.project2.dto.UpdatePasswordRequest;
import com.circuitcommerce.project2.exception.CircuitCommerceException;
import com.circuitcommerce.project2.exception.InvalidTokenException;
import com.circuitcommerce.project2.exception.PasswordTokenNotFoundException;
import com.circuitcommerce.project2.exception.TokenHasExpiredException;
import com.circuitcommerce.project2.model.Cart;
import com.circuitcommerce.project2.model.NotificationEmail;
import com.circuitcommerce.project2.model.PasswordResetToken;
import com.circuitcommerce.project2.model.RefreshToken;
import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.model.VerificationToken;
import com.circuitcommerce.project2.repository.CartRepository;
import com.circuitcommerce.project2.repository.PasswordTokenRepository;
import com.circuitcommerce.project2.repository.UserRepository;
import com.circuitcommerce.project2.repository.VerificationTokenRepository;
import com.circuitcommerce.project2.security.JwtProvider;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
  private final PasswordEncoder passwordEncoder;
  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final VerificationTokenRepository verificationTokenRepository;
  private final MailService mailService;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;
  private final RefreshTokenService refreshTokenService;
  private final PasswordTokenRepository passwordTokenRepository;

  public void signup(final RegisterRequest registerRequest) {
    final String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
    final Cart cart = new Cart();
    final User user = User.builder().username(registerRequest.getUsername()).password(encodedPassword)
        .email(registerRequest.getEmail()).createdAt(Instant.now()).isEnabled(false).cart(cart).build();
    cartRepository.save(cart);
    cart.setUser(user);
    userRepository.save(user);
    cartRepository.save(cart);

    final String token = generateVerificationToken(user);
    System.out.println("http://ec2-54-221-91-237.compute-1.amazonaws.com:8080/api/auth/account-verification/" + token);
    // mailService.sendMail(new NotificationEmail("Please activate your account", // Subject
    //     user.getEmail(), // Recipient
    //     // Body
    //     "Thank you for signing up with Circuit Commerce, in order to get started"
    //         + "please click the link below to activate your account."
    //         + "http://ec2-54-221-91-237.compute-1.amazonaws.com:8080/api/auth/account-verification/" + token));
  }

  public void verifyAccount(final String token) {
    final VerificationToken verToken = verificationTokenRepository.findByToken(token)
        .orElseThrow(() -> new InvalidTokenException());

    if (isExpiredToken(verToken.getExpiryDate())) {
      throw new TokenHasExpiredException("Verification Token has expired");
    } else {
      fetchUserAndEnable(verToken);
    }
  }

  @Transactional(readOnly = true)
  public User getCurrentUser() {
    final String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal()).getUsername();

    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
  }

  public AuthenticationResponse login(final LoginRequest loginRequest) {
    final String username = loginRequest.getUsername();
    final String password = loginRequest.getPassword();
    final Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(username, password));

    final Long jwtExpiration = jwtProvider.getJwtExpiration();
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final RefreshToken refreshToken = refreshTokenService.generateRefreshToken();
    final String jwtToken = jwtProvider.generateToken(authentication);
    return AuthenticationResponse.builder().authenticationToken(jwtToken).username(username)
        .refreshToken(refreshToken.getToken()).expiresAt(Instant.now().plusSeconds(jwtExpiration)).build();
  }

  public boolean isLoggedIn() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
  }

  public AuthenticationResponse refreshToken(final RefreshTokenRequest refreshTokenRequest) {
    final String refreshToken = refreshTokenRequest.getRefreshToken();
    refreshTokenService.validateRefreshToken(refreshToken);

    final String username = refreshTokenRequest.getUsername();
    final Long jwtExpiration = jwtProvider.getJwtExpiration();
    final String jwtToken = jwtProvider.generateTokenWithUsername(username);
    return AuthenticationResponse.builder().authenticationToken(jwtToken).refreshToken(refreshToken)
        .expiresAt(Instant.now().plusSeconds(jwtExpiration)).username(username).build();
  }

  public void resetPassword(final ResetPasswordRequest resetPasswordRequest) {
    final String username = resetPasswordRequest.getUsername();
    final String email = resetPasswordRequest.getEmail();
    final User user = userRepository.findByUsernameAndEmail(username, email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found when grabbing by both username and email"));

    final String token = generatePasswordResetToken(user);

    System.out
        .println("http://ec2-54-221-91-237.compute-1.amazonaws.com:8080/api/auth/account-reset-password/" + token);
    // mailService.sendMail(new NotificationEmail(
    // "Reset Your Password for your Account with Circuit Commerce", // subject
    // user.getEmail(), // recipient
    // "Here is a link to reset your password: " //TODO create a token for password
    // + "http://ec2-54-221-91-237.compute-1.amazonaws.com:8080/api/auth/account-reset-password/"
    // + token
    // ));
  }

  public PasswordResetToken verifyPasswordToken(final String token) {
    final PasswordResetToken passToken = passwordTokenRepository.findByToken(token)
        .orElseThrow(() -> new PasswordTokenNotFoundException("No reset password token was found for token: " + token));
    if (isExpiredToken(passToken.getExpiryDate())) {
      throw new TokenHasExpiredException("password token has expired");
    }
    return passToken;
  }

  public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
    PasswordResetToken passToken = verifyPasswordToken(updatePasswordRequest.getToken());
    User user = passToken.getUser();
    user.setPassword(passwordEncoder.encode(updatePasswordRequest.getPassword()));
    userRepository.save(user);
  }

  private boolean isExpiredToken(final Instant expiryDate) {
    final Instant now = Instant.now();
    return expiryDate.isBefore(now);
  }

  private String generatePasswordResetToken(final User user) {
    final String token = UUID.randomUUID().toString();
    final PasswordResetToken resetToken = PasswordResetToken.builder().user(user).token(token)
        .expiryDate(Instant.now().plusSeconds(PasswordResetToken.EXPIRATION)).build();

    passwordTokenRepository.save(resetToken);
    return token;
  }

  private String generateVerificationToken(final User user) {
    final String token = UUID.randomUUID().toString();
    final VerificationToken verificationToken = VerificationToken.builder().token(token).user(user)
        .expiryDate(Instant.now().plusSeconds(VerificationToken.EXPIRATION)).build();

    verificationTokenRepository.save(verificationToken);
    return token;
  }

  @Transactional
  private void fetchUserAndEnable(final VerificationToken verificationToken) {
    @NotBlank(message = "Username is required")
    final String username = verificationToken.getUser().getUsername();
    final User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new CircuitCommerceException("AuthService: Could not find user " + username));
    user.setEnabled(true);
    userRepository.save(user);
  }
}
