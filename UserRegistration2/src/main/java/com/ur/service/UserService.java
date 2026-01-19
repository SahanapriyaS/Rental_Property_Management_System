package com.ur.service;

import com.ur.dto.SignupRequest;
import com.ur.dto.UpdateUserRequest;
import com.ur.dto.UserDetailsDTO;
import com.ur.entity.User;
import com.ur.enums.Role;

import jakarta.validation.Valid;

public interface UserService {
	
	UserDetailsDTO getUserByIdAndRole(Long id, Role role);

    UserDetailsDTO getUserByEmailAndRole(String email, Role role);

    User getCurrentUser();

    void registerUser(SignupRequest request);

	Object updateCurrentUser(@Valid UpdateUserRequest request);

}
