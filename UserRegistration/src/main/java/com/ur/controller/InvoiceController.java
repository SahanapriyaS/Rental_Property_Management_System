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

import com.ur.dto.InvoiceRequestDTO;
import com.ur.dto.response.InvoiceResponseDTO;
import com.ur.entity.Invoice;
import com.ur.mapper.InvoiceMapper;
import com.ur.service.InvoiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
    private InvoiceService service;
	
	@Autowired
    private InvoiceMapper mapper;

    

    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@Valid @RequestBody InvoiceRequestDTO request) {
        Invoice invoice = mapper.toEntity(request);
        Invoice saved = service.create(invoice, request.getLeaseId());
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public List<InvoiceResponseDTO> listInvoices() {
        return service.getAllInvoices().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InvoiceResponseDTO> updateStatus(@PathVariable Long id,
                                                           @RequestParam String status) {
        Invoice updated = service.updateStatus(id, status);
        return ResponseEntity.ok(mapper.toDto(updated));
    }
}
