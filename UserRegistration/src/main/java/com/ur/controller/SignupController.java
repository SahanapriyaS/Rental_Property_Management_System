package com.ur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ur.dto.SignupRequest;
import com.ur.service.UserService;

@RestController
public class SignupController {

	@Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignupRequest request) {
    	if(request.getUsername() == null || request.getUsername().isEmpty()){
            return ResponseEntity.badRequest().body("Username is required");
        }
        userService.registerUser(request);
        return ResponseEntity.ok("User registered");
    }
}
