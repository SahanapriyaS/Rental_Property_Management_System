package com.ur.service;

import java.util.List;

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

	@Autowired
    private TenantRepository tenantRepo;
	
	@Autowired
    private UserRepository userRepo;
	
	@Autowired
    private PropertyRepository propertyRepo;

    public TenantService(TenantRepository tenantRepo, 
                         UserRepository userRepo,
                         PropertyRepository propertyRepo) {
        this.tenantRepo = tenantRepo;
        this.userRepo = userRepo;
        this.propertyRepo = propertyRepo;
    }

    public Tenant create(Long userId, Long propertyId, Tenant tenant) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        tenant.setUser(user);
        tenant.setProperty(property);

        return tenantRepo.save(tenant);
    }

    public List<Tenant> getAllTenants() {
        return tenantRepo.findAll();
    }

    public List<Tenant> getTenantsByVerification(boolean verified) {
        return tenantRepo.findByVerified(verified);
    }

    public Tenant getTenantById(Long id) {
        return tenantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
    }

    public Tenant updateVerificationStatus(Long tenantId, boolean verified) {
        Tenant tenant = getTenantById(tenantId);
        tenant.setVerified(verified);
        return tenantRepo.save(tenant);
    }
}
