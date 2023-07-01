package com.matty.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matty.security.config.JwtService;
import com.matty.security.user.Role;
import com.matty.security.user.User;
import com.matty.security.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository fUserRepository;
	private final PasswordEncoder fPasswordEncoder;
	private final JwtService fJwtService;
	private final AuthenticationManager fAuthenticationManager;
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email((request.getEmail()))
				.password(fPasswordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		fUserRepository.save(user);
		var jwt =  fJwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwt).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		fAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		var user = fUserRepository.findUserByEmail(request.getEmail())
				.orElseThrow();

		var jwt =  fJwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwt).build();
	}
}
