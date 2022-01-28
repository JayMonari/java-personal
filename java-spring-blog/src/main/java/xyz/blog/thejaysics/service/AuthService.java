package xyz.blog.thejaysics.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.blog.thejaysics.dto.AuthenticationResponse;
import xyz.blog.thejaysics.dto.LoginRequest;
import xyz.blog.thejaysics.dto.RegisterRequest;
import xyz.blog.thejaysics.model.User;
import xyz.blog.thejaysics.repository.UserRepository;
import xyz.blog.thejaysics.security.JwtProvider;

@Service
@AllArgsConstructor
public class AuthService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;

	public void signup(RegisterRequest registerRequest) {
		User user = User.builder()
			.username(registerRequest.getUsername())
			.email(registerRequest.getEmail())
			.password(passwordEncoder.encode(registerRequest.getPassword()))
			.build();
		userRepository.save(user);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String authenticationToken = jwtProvider.generateToken(authentication);
		return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
	}

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User principal =
			(org.springframework.security.core.userdetails.User)
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
	}
}
