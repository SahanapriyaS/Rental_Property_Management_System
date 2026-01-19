package com.ur.service;

import com.ur.entity.Invoice;
import com.ur.entity.Payment;
import com.ur.repository.InvoiceRepository;
import com.ur.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    private Invoice invoice;
    private Payment payment;

    @BeforeEach
    void setup() {
        invoice = new Invoice();
        invoice.setAmount(15000);
        invoice.setDueDate(LocalDate.now().plusDays(30));
        invoice.setStatus("DUE");
        invoice = invoiceRepository.save(invoice);

        payment = new Payment();
        payment.setAmount(15000);
        payment.setPaymentDate(LocalDate.now());
        payment.setMode("ONLINE");
    }

    @Test
    void testCreatePayment() {
        Payment saved = paymentService.create(payment, invoice.getId());

        assertNotNull(saved.getId());
        assertEquals("PAID", saved.getStatus());
        assertEquals(invoice.getId(), saved.getInvoice().getId());
    }

    @Test
    void testGetAllPayments() {
        paymentService.create(payment, invoice.getId());

        List<Payment> allPayments = paymentService.getAllPayments();
        assertEquals(2, allPayments.size());
    }

    @Test
    void testGetPaymentById() {
        Payment saved = paymentService.create(payment, invoice.getId());

        Payment fetched = paymentService.getPaymentById(saved.getId());
        assertEquals(saved.getId(), fetched.getId());
        assertEquals("PAID", fetched.getStatus());
    }

    @Test
    void testUpdateStatus() {
        Payment saved = paymentService.create(payment, invoice.getId());

        Payment updated = paymentService.updateStatus(saved.getId(), "REFUNDED");
        assertEquals("REFUNDED", updated.getStatus());
    }
}
