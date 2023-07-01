package com.matty.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService fAuthenticationService;

	@RequestMapping("/register")
	public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(fAuthenticationService.register(request));
	}

	@RequestMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> register (@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(fAuthenticationService.authenticate(request));
	}
}
