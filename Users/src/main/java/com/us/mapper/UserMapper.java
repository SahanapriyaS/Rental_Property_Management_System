package com.us.mapper;

import org.springframework.stereotype.Component;

import com.us.dto.request.UserRequest;
import com.us.dto.response.UserResponse;
import com.us.entity.User;
import com.us.enums.Role;

@Component
public class UserMapper {
	
	public User toEntity(UserRequest req) {
		User user = new User();
		user.setName(req.getName());
		user.setUserName(req.getUsername());
		user.setUserEmail(req.getUserEmail());
		user.setPhoneNumber(req.getPhoneNumber());
		user.setPassword(req.getPassword());
		user.setRole(req.getRole() != null ? req.getRole() : Role.TENANT);
		return user;
	}
	
	public UserResponse toResponse(User user) {
		UserResponse res = new UserResponse();
		res.setUserId(user.getUserId());
		res.setUserName(user.getUserName());
		res.setName(user.getName());
		res.setUserEmail(user.getUserEmail());
		res.setPhoneNumber(user.getPhoneNumber());
		res.setRole(user.getRole());
		return res;
	}

}
