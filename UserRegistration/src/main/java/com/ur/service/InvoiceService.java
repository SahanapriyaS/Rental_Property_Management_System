package com.ur.service;

import java.util.List;

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

	@Autowired
    private InvoiceRepository invoiceRepo;
	
	@Autowired
    private LeaseRepository leaseRepo;


    public Invoice create(Invoice invoice, Long leaseId) {
        Lease lease = leaseRepo.findById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));

        invoice.setLease(lease);
        invoice.setStatus("DUE");
        return invoiceRepo.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    public Invoice updateStatus(Long id, String status) {
        Invoice invoice = getInvoiceById(id);
        invoice.setStatus(status);
        return invoiceRepo.save(invoice);
    }
}
