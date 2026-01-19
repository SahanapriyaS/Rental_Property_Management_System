package com.ur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.ur.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);


}
