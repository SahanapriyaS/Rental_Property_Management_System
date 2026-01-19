package com.ur.service;

import com.ur.dto.SignupRequest;
import com.ur.entity.Authority;
import com.ur.entity.User;
import com.ur.enums.Role;
import com.ur.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private SignupRequest signupRequest;

    @BeforeEach
    void setup() {
        signupRequest = new SignupRequest();
        signupRequest.setUsername("user001");
        signupRequest.setPassword("password123");
        signupRequest.setName("Sahana");
        signupRequest.setEmail("sahana@example.com");
        signupRequest.setPhone(9876543210L);
        signupRequest.setRole(Role.TENANT);
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll(); 
    }

    @Test
    void testRegisterUser_Success() {
        String result = authService.registerUser(signupRequest);
        assertEquals("User registered successfully", result);

        Optional<User> savedOpt = userRepository.findByUsername("user001");
        assertTrue(savedOpt.isPresent());

        User saved = savedOpt.get();
        assertEquals("Sahana", saved.getName());
        assertEquals("sahana@example.com", saved.getEmail());
        assertTrue(passwordEncoder.matches("password123", saved.getPassword()));

        Role role = saved.getAuthorities().stream()
                .map(Authority::getAuthority)
                .findFirst()
                .orElseThrow();
        assertEquals(Role.TENANT, role);
    }

    @Test
    void testRegisterUser_UsernameExists() {
        authService.registerUser(signupRequest);

        String result = authService.registerUser(signupRequest);
        assertEquals("Username already exists", result);
    }
}
