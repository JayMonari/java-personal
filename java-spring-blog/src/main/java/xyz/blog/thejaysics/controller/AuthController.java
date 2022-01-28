package xyz.blog.thejaysics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.blog.thejaysics.dto.LoginRequest;
import xyz.blog.thejaysics.dto.RegisterRequest;
import xyz.blog.thejaysics.service.AuthService;
import xyz.blog.thejaysics.dto.AuthenticationResponse;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
}
