package com.ur.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ur.config.CurrentUserUtil;
import com.ur.dto.SignupRequest;
import com.ur.dto.UpdateUserRequest;
import com.ur.dto.UserDetailsDTO;
import com.ur.entity.Authority;
import com.ur.entity.User;
import com.ur.enums.Role;
import com.ur.mapper.UserMapper;
import com.ur.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private CurrentUserUtil currentUserUtil;
	
	@Autowired
    private UserMapper userMapper;

   

    @Override
    public UserDetailsDTO getUserByIdAndRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        validateRole(user, role);
        return userMapper.toDto(user);
    }

    @Override
    public UserDetailsDTO getUserByEmailAndRole(String email, Role role) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        validateRole(user, role);
        return userMapper.toDto(user);
    }

    @Override
    public User getCurrentUser() {
        return currentUserUtil.getUser();
    }

    @Override
    public void registerUser(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
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
    }

    private void validateRole(User user, Role role) {
        boolean hasRole = user.getAuthorities()
                .stream()
                .map(Authority::getAuthority)
                .anyMatch(r -> r == role);

        if (!hasRole) {
            throw new RuntimeException("User is not a " + role);
        }
    }
    
    @Transactional
    public UserDetailsDTO updateCurrentUser(UpdateUserRequest request) {

        User user = currentUserUtil.getUser();
        if (user == null) {
            throw new RuntimeException("User not authenticated");
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        userRepository.save(user);

        Set<Role> roles = user.getAuthorities()
                .stream()
                .map(Authority::getAuthority)
                .collect(Collectors.toSet());

        return new UserDetailsDTO(
        		user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getProvider(),
                user.isEnabled(),
                roles
        );
    }

}

