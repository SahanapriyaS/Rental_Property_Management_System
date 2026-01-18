package com.py.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.py.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
