package com.ur.mapper;

import org.springframework.stereotype.Component;

import com.ur.dto.MaintenanceRequestDTO;
import com.ur.dto.response.MaintenanceResponseDTO;
import com.ur.entity.MaintenanceTicket;

@Component
public class MaintenanceMapper {

    private final PropertyMapper propertyMapper;

    public MaintenanceMapper(PropertyMapper propertyMapper) {
        this.propertyMapper = propertyMapper;
    }

    public MaintenanceTicket toEntity(MaintenanceRequestDTO dto) {
        MaintenanceTicket ticket = new MaintenanceTicket();
        ticket.setCategory(dto.getCategory());
        ticket.setPriority(dto.getPriority());
        ticket.setDescription(dto.getDescription());
        ticket.setStatus("OPEN"); // default
        return ticket;
    }

    public MaintenanceResponseDTO toDto(MaintenanceTicket ticket) {
        MaintenanceResponseDTO dto = new MaintenanceResponseDTO();
        dto.setId(ticket.getId());
        dto.setCategory(ticket.getCategory());
        dto.setPriority(ticket.getPriority());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());

        if (ticket.getProperty() != null) {
            dto.setProperty(propertyMapper.toDto(ticket.getProperty()));
        }

        return dto;
    }
}
