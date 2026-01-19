package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Property;
import com.ur.entity.Tenant;
import com.ur.entity.User;
import com.ur.repository.PropertyRepository;
import com.ur.repository.TenantRepository;
import com.ur.repository.UserRepository;

@Service
@Transactional
public class TenantService {

    private static final Logger logger = LoggerFactory.getLogger(TenantService.class);

    @Autowired
    private TenantRepository tenantRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PropertyRepository propertyRepo;

    public Tenant create(Long userId, Long propertyId, Tenant tenant) {
        logger.info("Creating tenant for userId={} and propertyId={}", userId, propertyId);

        User user = userRepo.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with id={}", userId);
                    return new RuntimeException("User not found");
                });

        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> {
                    logger.error("Property not found with id={}", propertyId);
                    return new RuntimeException("Property not found");
                });

        tenant.setUser(user);
        tenant.setProperty(property);

        Tenant savedTenant = tenantRepo.save(tenant);
        logger.info("Tenant created successfully with id={}", savedTenant.getId());

        return savedTenant;
    }

    public List<Tenant> getAllTenants() {
        logger.info("Fetching all tenants");
        return tenantRepo.findAll();
    }

    public List<Tenant> getTenantsByVerification(boolean verified) {
        logger.info("Fetching tenants with verified={}", verified);
        return tenantRepo.findByVerified(verified);
    }

    public Tenant getTenantById(Long id) {
        logger.info("Fetching tenant with id={}", id);

        return tenantRepo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Tenant not found with id={}", id);
                    return new RuntimeException("Tenant not found");
                });
    }

    public Tenant updateVerificationStatus(Long tenantId, boolean verified) {
        logger.info("Updating tenant verification status. tenantId={}, verified={}", tenantId, verified);

        Tenant tenant = getTenantById(tenantId);
        tenant.setVerified(verified);

        Tenant updatedTenant = tenantRepo.save(tenant);
        logger.info("Tenant verification status updated successfully for id={}", updatedTenant.getId());

        return updatedTenant;
    }
}
