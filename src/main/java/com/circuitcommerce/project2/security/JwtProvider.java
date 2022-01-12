package com.circuitcommerce.project2.security;

import static java.util.Date.from;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;

import javax.annotation.PostConstruct;

import com.circuitcommerce.project2.exception.CircuitCommerceException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {
  private KeyStore keyStore;
  @Value("${jwt.expiration.time}")
  private Long jwtExpiration;

  @PostConstruct
  public void init() {
    try {
      keyStore = KeyStore.getInstance("JKS");
      InputStream resourceAsStream =
          getClass().getResourceAsStream("/circom.jks");
      keyStore.load(resourceAsStream, "secret".toCharArray());
    } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
      throw new CircuitCommerceException(
        "Exception occurred while loading keystore");
    }
  }

  public String generateToken(Authentication authentication) {
    User principal = (User) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject(principal.getUsername())
        .setIssuedAt(from(Instant.now()))
        .signWith(getPrivateKey())
        .setExpiration(Date.from(
            Instant.now().plusSeconds(jwtExpiration)))
        .compact();
  }

  public String generateTokenWithUsername(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(from(Instant.now()))
        .signWith(getPrivateKey())
        .setExpiration(Date.from(
            Instant.now().plusSeconds(jwtExpiration)))
        .compact();
  }

  public boolean validateToken(String jwt) {
    Jwts.parserBuilder()
        .setSigningKey(getPublicKey())
        .build()
        .parseClaimsJws(jwt);
    return true;
  }

  public String getUsernameFromJwt(String jwt) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(getPublicKey())
        .build()
        .parseClaimsJws(jwt)
        .getBody();
    return claims.getSubject();
  }

  public Long getJwtExpiration() {
    return jwtExpiration;
  }

  private PublicKey getPublicKey() {
    try {
      return keyStore.getCertificate("circom").getPublicKey();
    } catch (KeyStoreException e) {
      throw new CircuitCommerceException(
        "Exception occured when retrieving public key");
    }
  }

  private PrivateKey getPrivateKey() {
    try {
      return (PrivateKey) keyStore.getKey("circom", "secret".toCharArray());
    } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
      throw new CircuitCommerceException(
        "Exception occured while retriving private key");
    }
  }
}
