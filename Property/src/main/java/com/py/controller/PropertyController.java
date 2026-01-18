package com.py.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.py.dto.response.PropertyResponse;
import com.py.entity.Property;
import com.py.service.PropertyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyResponse> addProperty(@Valid @RequestBody Property property,
                                                        HttpServletRequest request) {
    	Long ownerId = (Long) request.getAttribute("userId");
        property.setOwnerId(ownerId);
        PropertyResponse response = propertyService.addProperty(property);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable Long id,
                                                            HttpServletRequest request) {
        String token = extractToken(request);
        PropertyResponse response = propertyService.getPropertyById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getAllProperties(HttpServletRequest request) {
        String token = extractToken(request);
        List<PropertyResponse> response = propertyService.getAllProperties();
        return ResponseEntity.ok(response);
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isBlank()) {
            throw new RuntimeException("Missing Authorization header");
        }
        return authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
    }
}
