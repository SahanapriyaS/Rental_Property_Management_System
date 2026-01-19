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

import com.ur.dto.MaintenanceRequestDTO;
import com.ur.dto.response.MaintenanceResponseDTO;
import com.ur.entity.MaintenanceTicket;
import com.ur.mapper.MaintenanceMapper;
import com.ur.service.MaintenanceService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

	@Autowired
    private MaintenanceService service;
	
	@Autowired
    private MaintenanceMapper mapper;


    @PostMapping
    public ResponseEntity<MaintenanceResponseDTO> createTicket(@Valid @RequestBody MaintenanceRequestDTO request) {
        MaintenanceTicket ticket = mapper.toEntity(request);
        MaintenanceTicket saved = service.create(ticket, request.getPropertyId());
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public List<MaintenanceResponseDTO> listTickets() {
        return service.getAllTickets().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MaintenanceResponseDTO> updateStatus(@PathVariable Long id,
                                                                @RequestParam String status) {
        MaintenanceTicket updated = service.updateStatus(id, status);
        return ResponseEntity.ok(mapper.toDto(updated));
    }
}
