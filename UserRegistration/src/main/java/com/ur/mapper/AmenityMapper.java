package com.ur.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ur.dto.AmenityRequestDTO;
import com.ur.dto.response.AmenityResponseDTO;
import com.ur.entity.Amenity;

@Component
public class AmenityMapper {

	@Autowired
    private PropertyMapper propertyMapper;

    public Amenity toEntity(AmenityRequestDTO dto) {
        Amenity a = new Amenity();
        a.setName(dto.getName());
        a.setDescription(dto.getDescription());
        return a;
    }

    public AmenityResponseDTO toDto(Amenity a) {
        AmenityResponseDTO dto = new AmenityResponseDTO();
        dto.setId(a.getId());
        dto.setName(a.getName());
        dto.setDescription(a.getDescription());
        if (a.getProperty() != null) {
            dto.setProperty(propertyMapper.toDto(a.getProperty()));
        }
        return dto;
    }
}
