package xyz.blog.thejaysics.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import xyz.blog.thejaysics.exception.JaysicsBlogException;

@Service
public class JwtProvider {
	private KeyStore keyStore;

	@PostConstruct
	public void init() {
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resource = getClass().getResourceAsStream("/jaysics-blog.jks");
			keyStore.load(resource, "secret".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new JaysicsBlogException("Exception occured while loading keystore");
		}
	}

	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
			.setSubject(principal.getUsername())
			.signWith(getPrivateKey())
			.compact();
	}

	public boolean validateToken(String jwt) {
		Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
		return true;
	}

	public String getUsernameFromJwt(String jwt) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getPublicKey()).build()
			.parseClaimsJws(jwt)
			.getBody();
		return claims.getSubject();
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new JaysicsBlogException("Exception occured while retrieving public key from keystore");
		}
	}

	private PublicKey getPublicKey() {
		try {
			return keyStore.getCertificate("jaysics-blog").getPublicKey();
		} catch (KeyStoreException e) {
			throw new JaysicsBlogException("Exception occured while retrieving public key from keystore");
		}
	}
}
