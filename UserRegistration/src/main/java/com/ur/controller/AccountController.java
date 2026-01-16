
package com.ur.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ur.config.CurrentUserUtil;
import com.ur.dto.UserDetailsDTO;
import com.ur.entity.Authority;
import com.ur.entity.User;
import com.ur.enums.Role;
import com.ur.repository.UserRepository;

@RestController
public class AccountController {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> home() {
        User current = currentUserUtil.getUser();
        if (current == null) {
            return ResponseEntity.ok("Welcome home (not logged in)");
        }
        return ResponseEntity.ok("Welcome home " + current.getUsername());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> admin(@PathVariable Long id) {
        return getUserDetailsForRole(id, Role.ADMIN);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<?> owner(@PathVariable Long id) {
        return getUserDetailsForRole(id, Role.OWNER);
    }

    @GetMapping("/tenant/{id}")
    public ResponseEntity<?> tenant(@PathVariable Long id) {
        return getUserDetailsForRole(id, Role.TENANT);
    }

    private ResponseEntity<?> getUserDetailsForRole(Long id, Role requiredRole) {
        return userRepository.findById(id)
            .map(user -> {
                Set<Role> roles = user.getAuthorities()
                        .stream()
                        .map(Authority::getAuthority)
                        .collect(Collectors.toSet());

                if (!roles.contains(requiredRole)) {
                    return ResponseEntity.status(404).body("User with id " + id + " is not a " + requiredRole);
                }

                UserDetailsDTO dto = new UserDetailsDTO(
                    user.getUsername(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getProvider(),
                    user.isEnabled(),
                    roles
                );
                return ResponseEntity.ok(dto);
            })
            .orElseGet(() -> ResponseEntity.status(404).body("User not found with id: " + id));
    }
}
