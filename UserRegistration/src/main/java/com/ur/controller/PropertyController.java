package com.ur.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ur.dto.PropertyRequestDTO;
import com.ur.dto.response.PropertyResponseDTO;
import com.ur.entity.Property;
import com.ur.mapper.PropertyMapper;
import com.ur.service.PropertyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/properties")
public class PropertyController {

	@Autowired
    private PropertyService propertyService;
	
	@Autowired
    private PropertyMapper propertyMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(
            @Valid @RequestBody PropertyRequestDTO requestDTO,
            HttpServletRequest httpRequest) {
        Long ownerId = (Long) httpRequest.getAttribute("userId");

        Property property = propertyMapper.toEntity(requestDTO);
        Property saved = propertyService.createProperty(property, ownerId);

        return ResponseEntity.ok(propertyMapper.toDto(saved));
    }


    @GetMapping
    public List<PropertyResponseDTO> getAllProperties() {
        return propertyService.getAllProperties()
                .stream()
                .map(propertyMapper::toDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public PropertyResponseDTO getProperty(@PathVariable Long id) {
        return propertyMapper.toDto(
                propertyService.getPropertyById(id)
        );
    }


    @GetMapping("/city/{city}")
    public List<PropertyResponseDTO> getByCity(@PathVariable String city) {
        return propertyService.getPropertiesByCity(city)
                .stream()
                .map(propertyMapper::toDto)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok("Property deleted successfully");
    }
}
