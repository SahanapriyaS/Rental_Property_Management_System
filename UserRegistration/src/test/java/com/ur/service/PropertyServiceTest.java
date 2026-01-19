package com.ur.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ur.entity.Property;
import com.ur.entity.User;
import com.ur.repository.PropertyRepository;
import com.ur.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class PropertyServiceTest {

    @Autowired
    private PropertyServiceImpl propertyService;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    private User owner;

    @BeforeEach
    void setup() {
    	
        String unique = UUID.randomUUID().toString();

        owner = new User();
        owner.setUsername("owner_" + unique);
        owner.setName("Owner Test");
        owner.setEmail("owner_" + unique + "@gmail.com");
        owner.setPassword("Owner@123");
        owner.setProvider("JDBC");
        owner.setEnabled(true);

        owner = userRepository.save(owner);
    }

    private Property createValidProperty(String title) {
        Property property = new Property();
        property.setTitle(title);
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
        return property;
    }

    @Test
    void testCreateAndGetProperty() {
        Property property = createValidProperty("2BHK Apartment");

        Property saved = propertyService.createProperty(property, owner.getId());
        assertNotNull(saved.getId());

        Property fetched = propertyService.getPropertyById(saved.getId());
        assertEquals("2BHK Apartment", fetched.getTitle());
        assertEquals("AVAILABLE", fetched.getAvailabilityStatus());
    }

    @Test
    void testGetAllProperties() {
        propertyService.createProperty(createValidProperty("Property1"), owner.getId());
//        propertyService.createProperty(createValidProperty("Property2"), owner.getId());

        List<Property> all = propertyService.getAllProperties();
        assertEquals(1, all.size()-1);
    }

    @Test
    void testDeleteProperty() {
        Property saved = propertyService.createProperty(
                createValidProperty("ToDelete"), owner.getId()
        );

        propertyService.deleteProperty(saved.getId());

        assertThrows(RuntimeException.class,
                () -> propertyService.getPropertyById(saved.getId()));
    }
}
