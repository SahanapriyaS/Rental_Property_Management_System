package com.ur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ur.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByCityIgnoreCase(String city);

    List<Property> findByOwnerId(Long ownerId);
}
