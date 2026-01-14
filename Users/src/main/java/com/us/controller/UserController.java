package com.us.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.us.dto.request.UserRequest;
import com.us.dto.response.AuthResponse;
import com.us.dto.response.UserResponse;
import com.us.entity.User;
import com.us.exception.ResourceAlreadyExistsException;
import com.us.mapper.UserMapper;
import com.us.service.JwtService;
import com.us.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	private final JwtService jwtService;
	private final UserMapper mapper;
	public UserController(UserService userService, JwtService jwtService, UserMapper mapper) {
		super();
		this.userService = userService;
		this.jwtService = jwtService;
		this.mapper = mapper;
	}
	
	 @PostMapping("/register")
	    public UserResponse register(@RequestBody UserRequest request) throws ResourceAlreadyExistsException {
	        User user = userService.register(request);
	        return mapper.toResponse(user);
	    }
	 
	 @PostMapping("/login")
	    public AuthResponse login(@RequestBody UserRequest request) {
	        User user = userService.authenticate(
	                request.getUsername(),
	                request.getPassword()
	        );
	        return new AuthResponse(
	                jwtService.generateToken(user.getUserName()),
	                mapper.toResponse(user)
	        );
	    }

}
