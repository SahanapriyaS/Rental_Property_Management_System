package com.ur.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ur.service.PasswordService;

@RestController
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/auth/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        System.out.println("Email: " + email);
        return ResponseEntity.ok("Check console for token");
    }
    
//    @PostMapping("/auth/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//        String token = request.get("token");
//        String newPassword = request.get("newPassword");
//
//        if (!passwordService.isResetTokenValid(email, token)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid or expired token");
//        }
//
//        passwordService.updatePassword(email, newPassword);
//
//        passwordService.clearResetToken(email);
//
//        return ResponseEntity.ok("Password successfully updated");
//    }


    
}
