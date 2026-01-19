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

import com.ur.dto.LeaseRequestDTO;
import com.ur.dto.response.LeaseResponseDTO;
import com.ur.entity.Lease;
import com.ur.mapper.LeaseMapper;
import com.ur.service.LeaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leases")
public class LeaseController {

	@Autowired
    private LeaseService service;
	
	@Autowired
    private LeaseMapper mapper;


    @PostMapping
    public ResponseEntity<LeaseResponseDTO> createLease(@Valid @RequestBody LeaseRequestDTO request) {
        Lease lease = mapper.toEntity(request);
        Lease saved = service.create(lease, request.getPropertyId(), request.getTenantId());
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public List<LeaseResponseDTO> listLeases() {
        return service.getAllLeases().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LeaseResponseDTO> updateStatus(@PathVariable Long id,
                                                         @RequestParam String status) {
        Lease updated = service.updateStatus(id, status);
        return ResponseEntity.ok(mapper.toDto(updated));
    }
}
