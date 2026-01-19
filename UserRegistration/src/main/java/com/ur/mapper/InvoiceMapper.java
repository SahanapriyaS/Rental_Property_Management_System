package com.ur.mapper;

import org.springframework.stereotype.Component;

import com.ur.dto.InvoiceRequestDTO;
import com.ur.dto.response.InvoiceResponseDTO;
import com.ur.entity.Invoice;


@Component
public class InvoiceMapper {

    private final LeaseMapper leaseMapper;

    public InvoiceMapper(LeaseMapper leaseMapper) {
        this.leaseMapper = leaseMapper;
    }

    public Invoice toEntity(InvoiceRequestDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setAmount(dto.getAmount());
        invoice.setDueDate(dto.getDueDate());
        invoice.setStatus("DUE"); // default
        return invoice;
    }

    public InvoiceResponseDTO toDto(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setAmount(invoice.getAmount());
        dto.setDueDate(invoice.getDueDate());
        dto.setStatus(invoice.getStatus());

        if (invoice.getLease() != null) {
            dto.setLease(leaseMapper.toDto(invoice.getLease()));
        }

        return dto;
    }
}
