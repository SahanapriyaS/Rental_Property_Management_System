package com.ur.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ur.dto.SignupRequest;
import com.ur.entity.Authority;
import com.ur.entity.User;
import com.ur.repository.UserRepository;

@RestController
public class SignupController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public String registerUser(@RequestBody SignupRequest request) {
		
		if(userRepository.existsByUsername(request.getUsername())) {
			return "Username already exists";
		}
		
		User user = new User();
		user.setUserName(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(true);
		user.setProvider("JDBC");
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		
		Authority authority = new Authority();
		authority.setAuthority(request.getRole());
		authority.setUser(user);
		
		user.setAuthorities(Set.of(authority));
		
		userRepository.save(user);
		return "User registered successfully";
		
		
		
	}


}
