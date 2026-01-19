package com.ur.service;

import com.ur.entity.Services;
import com.ur.entity.Tenant;
import com.ur.repository.ServicesRepository;
import com.ur.repository.TenantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional 
class ServicesServiceTest {

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private TenantRepository tenantRepository;

    private Tenant tenant;
    private Services service;

    @BeforeEach
    void setup() {
        tenant = new Tenant();
        tenant.setEmergencyContact("9876543210");
        tenant.setEmploymentInfo("Software Engineer");
        tenant.setVerified(false);
        tenant = tenantRepository.save(tenant);

        service = new Services();
        service.setName("Cleaning Service");
        service.setDescription("Weekly cleaning for apartment");
    }

    @Test
    void testCreateService() {
        Services saved = servicesService.create(service, tenant.getId());

        assertNotNull(saved.getId());
        assertEquals("Cleaning Service", saved.getName());
        assertEquals(tenant.getId(), saved.getTenant().getId());
    }

    @Test
    void testGetAllServices() {
        servicesService.create(service, tenant.getId());
        List<Services> allServices = servicesService.getAll();
        assertEquals(2, allServices.size());
    }

    @Test
    void testGetServicesByTenant() {
        servicesService.create(service, tenant.getId());
        List<Services> tenantServices = servicesService.getByTenant(tenant.getId());
        assertEquals(1, tenantServices.size());
        assertEquals("Cleaning Service", tenantServices.get(0).getName());
    }

    @Test
    void testGetServiceById() {
        Services saved = servicesService.create(service, tenant.getId());
        Services fetched = servicesService.getById(saved.getId());
        assertEquals(saved.getId(), fetched.getId());
    }

    @Test
    void testUpdateService() {
        Services saved = servicesService.create(service, tenant.getId());
        saved.setDescription("Biweekly cleaning");
        Services updated = servicesService.update(saved);

        assertEquals("Biweekly cleaning", updated.getDescription());
    }

    @Test
    void testDeleteService() {
        Services saved = servicesService.create(service, tenant.getId());
        servicesService.delete(saved.getId());

        assertThrows(RuntimeException.class, () -> servicesService.getById(saved.getId()));
    }
}
