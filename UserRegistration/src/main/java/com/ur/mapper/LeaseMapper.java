package com.ur.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ur.dto.LeaseRequestDTO;
import com.ur.dto.response.LeaseResponseDTO;
import com.ur.entity.Lease;

@Component
public class LeaseMapper {

	@Autowired
    private TenantMapper tenantMapper;
    private final PropertyMapper propertyMapper;

    public LeaseMapper( PropertyMapper propertyMapper) {
        this.propertyMapper = propertyMapper;
    }

    public Lease toEntity(LeaseRequestDTO dto) {
        Lease lease = new Lease();
        lease.setStartDate(dto.getStartDate());
        lease.setEndDate(dto.getEndDate());
        lease.setMonthlyRent(dto.getMonthlyRent());
        lease.setDeposit(dto.getDeposit());
        lease.setStatus("PENDING");
        return lease;
    }

    public LeaseResponseDTO toDto(Lease lease) {
        LeaseResponseDTO dto = new LeaseResponseDTO();
        dto.setId(lease.getId());
        dto.setStartDate(lease.getStartDate());
        dto.setEndDate(lease.getEndDate());
        dto.setMonthlyRent(lease.getMonthlyRent());
        dto.setDeposit(lease.getDeposit());
        dto.setStatus(lease.getStatus());

        if (lease.getTenant() != null) {
            dto.setTenant(tenantMapper.toDto(lease.getTenant()));
        }
        if (lease.getProperty() != null) {
            dto.setProperty(propertyMapper.toDto(lease.getProperty()));
        }

        return dto;
    }
}
