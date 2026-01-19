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

import com.ur.dto.ServicesRequestDTO;
import com.ur.dto.response.ServicesResponseDTO;
import com.ur.entity.Services;
import com.ur.mapper.ServicesMapper;
import com.ur.service.ServicesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/services")
public class ServicesController {

	@Autowired
    private ServicesService service;
	
	@Autowired
    private ServicesMapper mapper;


    @PostMapping
    public ResponseEntity<ServicesResponseDTO> create(@Valid @RequestBody ServicesRequestDTO dto) {
        Services s = mapper.toEntity(dto);
        Services saved = service.create(s, dto.getTenantId());
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public List<ServicesResponseDTO> getAll() {
        return service.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/tenant/{tenantId}")
    public List<ServicesResponseDTO> getByTenant(@PathVariable Long tenantId) {
        return service.getByTenant(tenantId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicesResponseDTO> update(@PathVariable Long id, @RequestBody ServicesRequestDTO dto) {
        Services s = service.getById(id);
        s.setName(dto.getName());
        s.setDescription(dto.getDescription());
        Services updated = service.update(s);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Service deleted successfully");
    }
}
