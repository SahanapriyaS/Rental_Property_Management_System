package com.py.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.py.dto.response.OwnerDTO;
import com.py.dto.response.PropertyResponse;
import com.py.entity.Property;
import com.py.feign.UserOpenFeign;
import com.py.mapper.PropertyMapper;
import com.py.repository.PropertyRepository;

import jakarta.validation.Valid;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserOpenFeign userOpenFeign;

    @Autowired
    private PropertyMapper propertyMapper;

    public PropertyResponse addProperty(@Valid Property property) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String token = attrs.getRequest().getHeader("Authorization");

        Long ownerId = (Long) attrs.getRequest().getAttribute("userId");
        property.setOwnerId(ownerId);

        Property savedProperty = propertyRepository.save(property);

        OwnerDTO owner = userOpenFeign.getUserById(savedProperty.getOwnerId(), token);
        return propertyMapper.toResponse(savedProperty, owner);
    }

    public PropertyResponse getPropertyById(Long propertyId) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String token = attrs.getRequest().getHeader("Authorization");

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));
        OwnerDTO owner = userOpenFeign.getUserById(property.getOwnerId(), token);
        return propertyMapper.toResponse(property, owner);
    }

    public List<PropertyResponse> getAllProperties() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String token = attrs.getRequest().getHeader("Authorization");

        Long ownerId = (Long) attrs.getRequest().getAttribute("userId");

        List<Property> properties = propertyRepository.findAll()
                .stream()
                .filter(p -> p.getOwnerId().equals(ownerId))
                .collect(Collectors.toList());

        return properties.stream()
                .map(property -> {
                    OwnerDTO owner = userOpenFeign.getUserById(property.getOwnerId(), token);
                    return propertyMapper.toResponse(property, owner);
                })
                .collect(Collectors.toList());
    }
}
