package com.ur.mapper;

import org.springframework.stereotype.Component;

import com.ur.dto.TenantRequestDTO;
import com.ur.dto.UserDetailsDTO;
import com.ur.dto.response.TenantResponseDTO;
import com.ur.entity.Tenant;

@Component
public class TenantMapper {

    public Tenant toEntity(TenantRequestDTO dto) {
        Tenant t = new Tenant();
        t.setEmergencyContact(dto.getEmergencyContact());
        t.setEmploymentInfo(dto.getEmploymentInfo());
        t.setVerified(dto.isVerified());
        return t;
    }

    public TenantResponseDTO toDto(Tenant t) {
        TenantResponseDTO dto = new TenantResponseDTO();
        dto.setId(t.getId());
        dto.setEmergencyContact(t.getEmergencyContact());
        dto.setEmploymentInfo(t.getEmploymentInfo());
        dto.setVerified(t.isVerified());

        // Map UserDetailsDTO
        if (t.getUser() != null) {
            UserDetailsDTO userDto = new UserDetailsDTO();
            userDto.setUserId(t.getUser().getId());
            userDto.setUserName(t.getUser().getUsername());
            userDto.setName(t.getUser().getName());
            userDto.setEmail(t.getUser().getEmail());
            userDto.setPhone(t.getUser().getPhone());
            userDto.setProvider(t.getUser().getProvider());
            userDto.setEnabled(t.getUser().isEnabled());
            userDto.setRoles(t.getUser().getAuthorities() != null ?
                t.getUser().getAuthorities().stream()
                  .map(a -> a.getAuthority())
                  .collect(java.util.stream.Collectors.toSet())
                : null);
            dto.setUser(userDto);
        }

        dto.setPropertyId(t.getProperty() != null ? t.getProperty().getId() : null);
        return dto;
    }
}
