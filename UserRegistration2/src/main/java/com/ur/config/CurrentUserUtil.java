package com.ur.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ur.entity.User;
import com.ur.repository.UserRepository;

@Component
public class CurrentUserUtil {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || !auth.isAuthenticated()) {
			return null;
		
		}
		

    String username = auth.getName();
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.orElse(null);
    }

}



