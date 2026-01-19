package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Services;
import com.ur.entity.Tenant;
import com.ur.repository.ServicesRepository;
import com.ur.repository.TenantRepository;

@Service
@Transactional
public class ServicesService {

    private static final Logger logger = LoggerFactory.getLogger(ServicesService.class);

    @Autowired
    private ServicesRepository servicesRepo;
    
    @Autowired
    private TenantRepository tenantRepo;


    public Services create(Services service, Long tenantId) {
        logger.info("Creating service '{}' for tenantId={}", service.getName(), tenantId);

        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> {
                    logger.error("Tenant not found with id={}", tenantId);
                    return new RuntimeException("Tenant not found");
                });

        service.setTenant(tenant);
        Services saved = servicesRepo.save(service);

        logger.info("Service created successfully with id={}", saved.getId());
        return saved;
    }

    public List<Services> getAll() {
        logger.info("Fetching all services");
        return servicesRepo.findAll();
    }

    public List<Services> getByTenant(Long tenantId) {
        logger.info("Fetching services for tenantId={}", tenantId);
        return servicesRepo.findByTenantId(tenantId);
    }

    public Services getById(Long id) {
        logger.info("Fetching service with id={}", id);
        return servicesRepo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Service not found with id={}", id);
                    return new RuntimeException("Service not found");
                });
    }

    public Services update(Services service) {
        logger.info("Updating service with id={}", service.getId());
        return servicesRepo.save(service);
    }

    public void delete(Long id) {
        logger.info("Deleting service with id={}", id);
        servicesRepo.deleteById(id);
    }
}
