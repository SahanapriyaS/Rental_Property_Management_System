package com.ur.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ur.dto.AmenityRequestDTO;
import com.ur.dto.response.AmenityResponseDTO;
import com.ur.entity.Amenity;
import com.ur.mapper.AmenityMapper;
import com.ur.service.AmenityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/amenities")
public class AmenityController {

	@Autowired
    private AmenityService service;
	
	@Autowired
    private AmenityMapper mapper;

    

    @PostMapping
    public ResponseEntity<AmenityResponseDTO> create(@Valid @RequestBody AmenityRequestDTO dto) {
        Amenity amenity = mapper.toEntity(dto);
        Amenity saved = service.create(amenity, dto.getPropertyId());
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public List<AmenityResponseDTO> getAll() {
        return service.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/property/{propertyId}")
    public List<AmenityResponseDTO> getByProperty(@PathVariable Long propertyId) {
        return service.getByProperty(propertyId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenityResponseDTO> update(@PathVariable Long id, @RequestBody AmenityRequestDTO dto) {
        Amenity amenity = service.getById(id);
        amenity.setName(dto.getName());
        amenity.setDescription(dto.getDescription());
        Amenity updated = service.update(amenity);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Amenity deleted successfully");
    }
}
