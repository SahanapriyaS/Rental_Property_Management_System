package com.us.dto.response;

public class AuthResponse {
	
	private String token;
	private UserResponse user;
	public AuthResponse(String token, UserResponse user) {
		super();
		this.token = token;
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public UserResponse getUser() {
		return user;
	}
	
	

}
