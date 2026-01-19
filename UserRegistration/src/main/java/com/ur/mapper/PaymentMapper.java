package com.ur.mapper;

import org.springframework.stereotype.Component;

import com.ur.dto.PaymentRequestDTO;
import com.ur.dto.response.PaymentResponseDTO;
import com.ur.entity.Payment;


@Component
public class PaymentMapper {

    private final InvoiceMapper invoiceMapper;

    public PaymentMapper(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    public Payment toEntity(PaymentRequestDTO dto) {
        Payment p = new Payment();
        p.setAmount(dto.getAmount());
        p.setPaymentDate(dto.getPaymentDate());
        p.setMode(dto.getMode());
        p.setStatus("PAID"); // default
        return p;
    }

    public PaymentResponseDTO toDto(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setMode(payment.getMode());
        dto.setStatus(payment.getStatus());

        if (payment.getInvoice() != null) {
            dto.setInvoice(invoiceMapper.toDto(payment.getInvoice()));
        }

        return dto;
    }
}
