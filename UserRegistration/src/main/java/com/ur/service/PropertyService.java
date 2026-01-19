package com.ur.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ur.entity.Property;

public interface PropertyService {

    Property createProperty(Property property, Long ownerId);

    Property getPropertyById(Long id);

    List<Property> getAllProperties();

    List<Property> getPropertiesByCity(String city);

    void deleteProperty(Long id);
}
