package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Lease;
import com.ur.entity.Property;
import com.ur.entity.Tenant;
import com.ur.exception.ResourceNotFoundException;
import com.ur.repository.LeaseRepository;
import com.ur.repository.PropertyRepository;
import com.ur.repository.TenantRepository;

@Service
@Transactional
public class LeaseService {

    private static final Logger logger = LoggerFactory.getLogger(LeaseService.class);

    @Autowired
    private LeaseRepository leaseRepo;

    @Autowired
    private PropertyRepository propertyRepo;

    @Autowired
    private TenantRepository tenantRepo;

    public Lease create(Lease lease, Long propertyId, Long tenantId) {
        logger.info("Creating lease for propertyId={} and tenantId={}", propertyId, tenantId);

        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> {
                    logger.error("Property not found with id={}", propertyId);
                    return new RuntimeException("Property not found");
                });

        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> {
                    logger.error("Tenant not found with id={}", tenantId);
                    return new RuntimeException("Tenant not found");
                });

        lease.setProperty(property);
        lease.setTenant(tenant);
        lease.setStatus("PENDING");

        Lease savedLease = leaseRepo.save(lease);
        logger.info("Lease created successfully with id={}", savedLease.getId());

        return savedLease;
    }

    public List<Lease> getAllLeases() {
        logger.info("Fetching all leases");
        return leaseRepo.findAll();
    }

    public Lease getLeaseById(Long id) {
    	return leaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lease with id " + id + " not found"));

    }

    public Lease updateStatus(Long id, String status) {
        logger.info("Updating lease status. leaseId={}, newStatus={}", id, status);

        Lease lease = getLeaseById(id);
        lease.setStatus(status);

        Lease updatedLease = leaseRepo.save(lease);
        logger.info("Lease status updated successfully for id={}", updatedLease.getId());

        return updatedLease;
    }
}
