package com.us.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.us.dto.request.UserRequest;
import com.us.entity.User;
import com.us.exception.InvalidCredentialsException;
import com.us.exception.ResourceAlreadyExistsException;
import com.us.mapper.UserMapper;
import com.us.repository.UserRepository;

public class UserService {
	
	private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, UserMapper mapper) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.mapper = mapper;
	}


	public User register(UserRequest request) throws ResourceAlreadyExistsException {
		if(userRepository.existsByEmail(request.getUserEmail()))
			throw new ResourceAlreadyExistsException("Email already exists");
		
		if(userRepository.existsByPhone(request.getPhoneNumber()))
			throw new ResourceAlreadyExistsException("Phone number already exists");
		User user = mapper.toEntity(request);
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}


	public User authenticate(String username, String password) {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new InvalidCredentialsException("Invalid username"));
		
		if(!encoder.matches(password, user.getPassword()))
			throw new InvalidCredentialsException("Invalid password");
		return user;
	}
	

}
