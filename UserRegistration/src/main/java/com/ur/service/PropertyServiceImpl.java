package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Property;
import com.ur.entity.User;
import com.ur.repository.PropertyRepository;
import com.ur.repository.UserRepository;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private UserRepository userRepository;


    @Override
    public Property createProperty(Property property, Long ownerId) {
        logger.info("Creating property '{}' for ownerId={}", property.getTitle(), ownerId);

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> {
                    logger.error("Owner not found with id={}", ownerId);
                    return new RuntimeException("Owner not found");
                });

        property.setOwner(owner);
        property.setAvailabilityStatus("AVAILABLE");

        Property savedProperty = propertyRepository.save(property);
        logger.info("Property saved with id={}", savedProperty.getId());
        return savedProperty;
    }

    @Override
    public Property getPropertyById(Long id) {
        logger.info("Fetching property with id={}", id);
        return propertyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Property not found with id={}", id);
                    return new RuntimeException("Property not found");
                });
    }

    @Override
    public List<Property> getAllProperties() {
        logger.info("Fetching all properties");
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> getPropertiesByCity(String city) {
        logger.info("Fetching properties in city '{}'", city);
        return propertyRepository.findByCityIgnoreCase(city);
    }

    @Override
    public void deleteProperty(Long id) {
        logger.info("Deleting property with id={}", id);
        propertyRepository.deleteById(id);
        logger.info("Property deleted with id={}", id);
    }
}
