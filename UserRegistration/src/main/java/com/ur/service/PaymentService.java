package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private InvoiceRepository invoiceRepo;

    public Payment create(Payment payment, Long invoiceId) {
        logger.info("Creating payment for invoiceId={}", invoiceId);

        Invoice invoice = invoiceRepo.findById(invoiceId)
                .orElseThrow(() -> {
                    logger.error("Invoice not found with id={}", invoiceId);
                    return new RuntimeException("Invoice not found");
                });

        payment.setInvoice(invoice);
        payment.setStatus("PAID");

        Payment savedPayment = paymentRepo.save(payment);
        logger.info("Payment created successfully with id={}", savedPayment.getId());

        return savedPayment;
    }

    public List<Payment> getAllPayments() {
        logger.info("Fetching all payments");
        return paymentRepo.findAll();
    }

    public Payment getPaymentById(Long id) {
        logger.info("Fetching payment with id={}", id);

        return paymentRepo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Payment not found with id={}", id);
                    return new RuntimeException("Payment not found");
                });
    }

    public Payment updateStatus(Long id, String status) {
        logger.info("Updating payment status. paymentId={}, newStatus={}", id, status);

        Payment payment = getPaymentById(id);
        payment.setStatus(status);

        Payment updatedPayment = paymentRepo.save(payment);
        logger.info("Payment status updated successfully for id={}", updatedPayment.getId());

        return updatedPayment;
    }
}
