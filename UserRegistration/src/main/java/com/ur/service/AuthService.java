package com.ur.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.dto.SignupRequest;
import com.ur.entity.Authority;
import com.ur.entity.User;
import com.ur.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String registerUser(@Valid SignupRequest request) {

        logger.info("Attempting to register user with username='{}'", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            logger.warn("Registration failed: Username '{}' already exists", request.getUsername());
            return "Username already exists";
        }

        User user = new User();
        user.setUsername(request.getUsername());
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

        logger.info("User '{}' registered successfully with role '{}'",
                user.getUsername(), request.getRole());

        return "User registered successfully";
    }
}
