package com.ur.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ur.dto.TenantRequestDTO;
import com.ur.dto.response.TenantResponseDTO;
import com.ur.entity.Tenant;
import com.ur.mapper.TenantMapper;
import com.ur.service.TenantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tenants")
public class TenantController {

	@Autowired
    private TenantService service;
	
	@Autowired
    private TenantMapper mapper;

    public TenantController(TenantService service, TenantMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TenantResponseDTO> createTenant(@Valid @RequestBody TenantRequestDTO request) {
        Tenant t = mapper.toEntity(request);
        Tenant saved = service.create(request.getUserId(), request.getPropertyId(), t);
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public List<TenantResponseDTO> listTenants() {
        return service.getAllTenants().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/verified/{status}")
    public List<TenantResponseDTO> listByVerification(@PathVariable boolean status) {
        return service.getTenantsByVerification(status).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/verify")
    public ResponseEntity<TenantResponseDTO> updateVerification(@PathVariable Long id, @RequestParam boolean status) {
        Tenant updated = service.updateVerificationStatus(id, status);
        return ResponseEntity.ok(mapper.toDto(updated));
    }
}
