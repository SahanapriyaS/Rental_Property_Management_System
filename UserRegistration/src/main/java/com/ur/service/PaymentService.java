package com.ur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Invoice;
import com.ur.entity.Payment;
import com.ur.repository.InvoiceRepository;
import com.ur.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {

	@Autowired
    private PaymentRepository paymentRepo;
	
	@Autowired
    private InvoiceRepository invoiceRepo;


    public Payment create(Payment payment, Long invoiceId) {
        Invoice invoice = invoiceRepo.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        payment.setInvoice(invoice);
        payment.setStatus("PAID");
        return paymentRepo.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment updateStatus(Long id, String status) {
        Payment payment = getPaymentById(id);
        payment.setStatus(status);
        return paymentRepo.save(payment);
    }
}
