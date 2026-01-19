package com.ur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Lease;
import com.ur.entity.Property;
import com.ur.entity.Tenant;
import com.ur.repository.LeaseRepository;
import com.ur.repository.PropertyRepository;
import com.ur.repository.TenantRepository;

@Service
@Transactional
public class LeaseService {

	@Autowired
    private LeaseRepository leaseRepo;
	
	@Autowired
    private PropertyRepository propertyRepo;
	
	@Autowired
    private TenantRepository tenantRepo;

   

    public Lease create(Lease lease, Long propertyId, Long tenantId) {
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        lease.setProperty(property);
        lease.setTenant(tenant);
        lease.setStatus("PENDING");
        return leaseRepo.save(lease);
    }

    public List<Lease> getAllLeases() {
        return leaseRepo.findAll();
    }

    public Lease getLeaseById(Long id) {
        return leaseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lease not found"));
    }

    public Lease updateStatus(Long id, String status) {
        Lease lease = getLeaseById(id);
        lease.setStatus(status);
        return leaseRepo.save(lease);
    }
}
