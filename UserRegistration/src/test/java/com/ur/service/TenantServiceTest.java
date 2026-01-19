package com.ur.service;

import com.ur.entity.Property;
import com.ur.entity.Tenant;
import com.ur.entity.User;
import com.ur.repository.PropertyRepository;
import com.ur.repository.TenantRepository;
import com.ur.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  
class TenantServiceTest {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    private User user;
    private Property property;
    private Tenant tenant;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUsername("tenantUser");
        user.setName("Tenant User");
        user.setEmail("tenant@example.com");
        user.setPassword("tenantpass");
        user.setProvider("JDBC");
        user.setEnabled(true);
        user = userRepository.save(user);

        property = new Property();
        property.setTitle("2BHK Apartment");
        property.setType("APARTMENT");
        property.setAddress("No.1, Test Street");
        property.setCity("Coimbatore");
        property.setState("TN");
        property.setZip("641001");
        property.setBedrooms(2);
        property.setBathrooms(2);
        property.setAreaSqft(900);
        property.setRentAmount(15000);
        property.setDepositAmount(30000);
        property.setAvailabilityStatus("AVAILABLE");
        property.setOwner(user);  
        property = propertyRepository.save(property);

        tenant = new Tenant();
        tenant.setEmergencyContact("9876543210");
        tenant.setEmploymentInfo("Software Engineer");
        tenant.setVerified(false);
    }

    @Test
    void testCreateTenant() {
        Tenant saved = tenantService.create(user.getId(), property.getId(), tenant);
        assertNotNull(saved.getId());
        assertEquals(user.getId(), saved.getUser().getId());
        assertEquals(property.getId(), saved.getProperty().getId());
        assertFalse(saved.isVerified());
    }




    @Test
    void testGetTenantById() {
        Tenant saved = tenantService.create(user.getId(), property.getId(), tenant);
        Tenant fetched = tenantService.getTenantById(saved.getId());
        assertEquals(saved.getId(), fetched.getId());
    }

    @Test
    void testUpdateVerificationStatus() {
        Tenant saved = tenantService.create(user.getId(), property.getId(), tenant);
        Tenant updated = tenantService.updateVerificationStatus(saved.getId(), true);
        assertTrue(updated.isVerified());

        Tenant fromDb = tenantRepository.findById(saved.getId()).orElseThrow();
        assertTrue(fromDb.isVerified());
    }
}
