package com.ur.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ur.dto.UpdateUserRequest;
import com.ur.dto.UserDetailsDTO;
import com.ur.entity.User;
import com.ur.enums.Role;
import com.ur.service.UserService;

import jakarta.validation.Valid;

@RestController
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> home() {
        User current = userService.getCurrentUser();
        return current == null
                ? ResponseEntity.ok("Welcome home (not logged in)")
                : ResponseEntity.ok("Welcome home " + current.getUsername());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDetailsDTO> admin(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByIdAndRole(id, Role.ADMIN));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<UserDetailsDTO> owner(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByIdAndRole(id, Role.OWNER));
    }

    @GetMapping("/tenant/{id}")
    public ResponseEntity<UserDetailsDTO> tenant(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByIdAndRole(id, Role.TENANT));
    }

    @GetMapping("/admin/email/{email}")
    public ResponseEntity<UserDetailsDTO> adminByEmail(@PathVariable String email) {
    	 UserDetailsDTO admin = userService.getUserByEmailAndRole(email, Role.ADMIN);
         if(admin == null) return ResponseEntity.notFound().build();
         return ResponseEntity.ok(admin);
    }
    
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/owner/email/{email}")
    public ResponseEntity<UserDetailsDTO> ownerByEmail(@PathVariable String email) {
    	 UserDetailsDTO user = userService.getUserByEmailAndRole(email, Role.OWNER);
         if(user == null) return ResponseEntity.notFound().build();
         return ResponseEntity.ok(user);   
    }
    
    @PreAuthorize("hasRole('TENANT')")
    @GetMapping("/tenant/email/{email}")
    public ResponseEntity<UserDetailsDTO> tenantByEmail(@PathVariable String email) {
    	 UserDetailsDTO user = userService.getUserByEmailAndRole(email, Role.TENANT);
         if(user == null) return ResponseEntity.notFound().build();
         return ResponseEntity.ok(user);    
    }
    
    @PutMapping("/user/profile")
    public ResponseEntity<Object> updateProfile(
            @Valid @RequestBody UpdateUserRequest request) {

        return ResponseEntity.ok(userService.updateCurrentUser(request));
    }

}
