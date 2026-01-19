package com.ur.service;

import com.ur.entity.Invoice;
import com.ur.entity.Lease;
import com.ur.repository.InvoiceRepository;
import com.ur.repository.LeaseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepo;
    @Mock
    private LeaseRepository leaseRepo;

    @InjectMocks
    private InvoiceService invoiceService;

    private Invoice invoice;
    private Lease lease;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        lease = new Lease();
        lease.setId(1L);

        invoice = new Invoice();
        invoice.setId(1L);
    }

    @Test
    void testCreateInvoice() {
        when(leaseRepo.findById(1L)).thenReturn(Optional.of(lease));
        when(invoiceRepo.save(any(Invoice.class))).thenReturn(invoice);

        Invoice result = invoiceService.create(invoice, 1L);
        assertEquals(1L, result.getId());
        assertEquals("DUE", result.getStatus());
    }

    @Test
    void testUpdateStatus() {
        invoice.setStatus("DUE");
        when(invoiceRepo.findById(1L)).thenReturn(Optional.of(invoice));
        when(invoiceRepo.save(any(Invoice.class))).thenReturn(invoice);

        Invoice updated = invoiceService.updateStatus(1L, "PAID");
        assertEquals("PAID", updated.getStatus());
    }
}
