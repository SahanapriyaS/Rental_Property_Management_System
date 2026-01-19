package com.ur.mapper;

import org.springframework.stereotype.Component;

import com.ur.dto.PropertyRequestDTO;
import com.ur.dto.response.PropertyResponseDTO;
import com.ur.entity.Property;

@Component
public class PropertyMapper {

    public Property toEntity(PropertyRequestDTO dto) {

        Property p = new Property();
        p.setTitle(dto.getTitle());
        p.setType(dto.getType());
        p.setAddress(dto.getAddress());
        p.setCity(dto.getCity());
        p.setState(dto.getState());
        p.setZip(dto.getZip());
        p.setBedrooms(dto.getBedrooms());
        p.setBathrooms(dto.getBathrooms());
        p.setAreaSqft(dto.getAreaSqft());
        p.setRentAmount(dto.getRentAmount());
        p.setDepositAmount(dto.getDepositAmount());

        return p;
    }

    public PropertyResponseDTO toDto(Property property) {

        PropertyResponseDTO dto = new PropertyResponseDTO();
        dto.setId(property.getId());
        dto.setTitle(property.getTitle());
        dto.setType(property.getType());
        dto.setAddress(property.getAddress());
        dto.setCity(property.getCity());
        dto.setState(property.getState());
        dto.setZip(property.getZip());
        dto.setBedrooms(property.getBedrooms());
        dto.setBathrooms(property.getBathrooms());
        dto.setAreaSqft(property.getAreaSqft());
        dto.setRentAmount(property.getRentAmount());
        dto.setDepositAmount(property.getDepositAmount());
        dto.setAvailabilityStatus(property.getAvailabilityStatus());

        dto.setOwnerId(property.getOwner().getId());
        dto.setOwnerUsername(property.getOwner().getUsername());

        return dto;
    }
}
