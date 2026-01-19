
package com.ur.service;

import java.util.Set;

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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String registerUser(@Valid SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
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
        return "User registered successfully";
    }

	
}
