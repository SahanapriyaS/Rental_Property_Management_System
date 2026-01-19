package com.ur.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ur.entity.PasswordResetToken;
import com.ur.entity.User;
import com.ur.repository.PasswordResetTokenRepository;
import com.ur.repository.UserRepository;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createPasswordResetToken(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); 

        tokenRepository.save(resetToken);

        System.out.println("Password reset token: " + token);
    }

//    public void resetPassword(String token, String newPassword) {
//        PasswordResetToken resetToken = tokenRepository.findByToken(token)
//            .orElseThrow(() -> new RuntimeException("Invalid token"));
//
//        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//            throw new RuntimeException("Token expired");
//        }
//
//        User user = resetToken.getUser();
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//
//        tokenRepository.delete(resetToken);
//    }
}

