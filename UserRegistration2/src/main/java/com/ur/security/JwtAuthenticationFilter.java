package com.ur.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ur.dto.LoginRequest;
import com.ur.dto.UserDetailsDTO;
import com.ur.entity.User;
import com.ur.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepo;
	
	public JwtAuthenticationFilter(AuthenticationManager authManager, JwtUtil jwtUtil, UserRepository userRepo) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
		this.userRepo=userRepo;
		setFilterProcessesUrl("/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			return authManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword())
					);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException {
		String username=authResult.getName();
		User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		 String role = authResult.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .findFirst()
	                .orElse("ADMIN");
		String token = jwtUtil.generateToken(authResult.getName(),user.getId(),role);

		response.setContentType("application/json");
		response.getWriter().write("{\"token\":\""+token+"\"}");
	}

}

