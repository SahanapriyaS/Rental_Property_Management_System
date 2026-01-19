package com.ur.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ur.dto.ServicesRequestDTO;
import com.ur.dto.response.ServicesResponseDTO;
import com.ur.entity.Services;

@Component
public class ServicesMapper {

	@Autowired
    private TenantMapper tenantMapper;

   

    public Services toEntity(ServicesRequestDTO dto) {
        Services s = new Services();
        s.setName(dto.getName());
        s.setDescription(dto.getDescription());
        return s;
    }

    public ServicesResponseDTO toDto(Services s) {
        ServicesResponseDTO dto = new ServicesResponseDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setDescription(s.getDescription());
        if (s.getTenant() != null) {
            dto.setTenant(tenantMapper.toDto(s.getTenant()));
        }
        return dto;
    }
}
