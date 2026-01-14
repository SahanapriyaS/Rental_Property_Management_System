package com.us.dto.response;

import com.us.enums.Role;

public class UserResponse {
	
	private Long userId;
	private String userName;
	private String name;
	private String userEmail;
	private String phoneNumber;
	private Role role;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String email) {
		this.userEmail = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	

}
