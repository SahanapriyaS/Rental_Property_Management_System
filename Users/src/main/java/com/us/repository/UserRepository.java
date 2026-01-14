package com.us.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String userEmail);

	boolean existsByPhone(String phoneNumber);

	Optional<User> findByUserName(String username);

}
