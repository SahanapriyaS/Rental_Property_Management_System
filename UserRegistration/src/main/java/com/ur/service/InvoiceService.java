package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Invoice;
import com.ur.entity.Lease;
import com.ur.repository.InvoiceRepository;
import com.ur.repository.LeaseRepository;

@Service
@Transactional
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    private LeaseRepository leaseRepo;

    public Invoice create(Invoice invoice, Long leaseId) {
        logger.info("Creating invoice for leaseId={}", leaseId);

        Lease lease = leaseRepo.findById(leaseId)
                .orElseThrow(() -> {
                    logger.error("Lease not found with id={}", leaseId);
                    return new RuntimeException("Lease not found");
                });

        invoice.setLease(lease);
        invoice.setStatus("DUE");

        Invoice savedInvoice = invoiceRepo.save(invoice);
        logger.info("Invoice created successfully with id={}", savedInvoice.getId());

        return savedInvoice;
    }

    public List<Invoice> getAllInvoices() {
        logger.info("Fetching all invoices");
        return invoiceRepo.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        logger.info("Fetching invoice with id={}", id);

        return invoiceRepo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Invoice not found with id={}", id);
                    return new RuntimeException("Invoice not found");
                });
    }

    public Invoice updateStatus(Long id, String status) {
        logger.info("Updating invoice status. invoiceId={}, newStatus={}", id, status);

        Invoice invoice = getInvoiceById(id);
        invoice.setStatus(status);

        Invoice updatedInvoice = invoiceRepo.save(invoice);
        logger.info("Invoice status updated successfully for id={}", updatedInvoice.getId());

        return updatedInvoice;
    }
}
