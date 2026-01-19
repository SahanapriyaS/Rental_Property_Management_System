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

import com.ur.dto.PaymentRequestDTO;
import com.ur.dto.response.PaymentResponseDTO;
import com.ur.entity.Payment;
import com.ur.mapper.PaymentMapper;
import com.ur.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
    private PaymentService service;
	
	@Autowired
    private PaymentMapper mapper;


    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO request) {
        Payment payment = mapper.toEntity(request);
        Payment saved = service.create(payment, request.getInvoiceId());
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @GetMapping
    public List<PaymentResponseDTO> listPayments() {
        return service.getAllPayments().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updateStatus(@PathVariable Long id,
                                                           @RequestParam String status) {
        Payment updated = service.updateStatus(id, status);
        return ResponseEntity.ok(mapper.toDto(updated));
    }
}
