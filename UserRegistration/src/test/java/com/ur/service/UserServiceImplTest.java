package com.ur.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ur.dto.SignupRequest;
import com.ur.entity.User;
import com.ur.enums.Role;
import com.ur.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

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
        signupRequest.setEmail("sana@example.com");
        signupRequest.setPhone(9876543210L);
        signupRequest.setRole(Role.TENANT);
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    private void authenticate(User user) {
        var authorities = user.getAuthorities().stream()
                .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.getAuthority().name()))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        authorities
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testRegisterUser() {
        userService.registerUser(signupRequest);

        User user = userRepository.findByUsername("user001").orElseThrow();
        Role role = user.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .findFirst()
                .orElseThrow();

        assertEquals(Role.TENANT, role);
        assertEquals("Sahana", user.getName());
        assertEquals("sana@example.com", user.getEmail());
        assertTrue(passwordEncoder.matches("password123", user.getPassword()));
    }

    @Test
    void testGetCurrentUser() {
        userService.registerUser(signupRequest);
        User user = userRepository.findByUsername("user001").orElseThrow();
        authenticate(user);

        User current = userService.getCurrentUser();
        assertNotNull(current);
        assertEquals("user001", current.getUsername());
        assertEquals("Sahana", current.getName());
    }


    
}
