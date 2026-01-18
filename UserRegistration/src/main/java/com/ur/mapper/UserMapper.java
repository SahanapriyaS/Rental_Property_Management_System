package com.ur.mapper;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ur.dto.UserDetailsDTO;
import com.ur.entity.Authority;
import com.ur.entity.User;
import com.ur.enums.Role;

@Component
public class UserMapper {

    public UserDetailsDTO toDto(User user) {
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
