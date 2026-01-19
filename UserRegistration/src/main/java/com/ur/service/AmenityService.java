package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.Amenity;
import com.ur.entity.Property;
import com.ur.repository.AmenityRepository;
import com.ur.repository.PropertyRepository;

@Service
@Transactional
public class AmenityService {

    private static final Logger logger = LoggerFactory.getLogger(AmenityService.class);

    @Autowired
    private AmenityRepository amenityRepo;
    
    @Autowired
    private PropertyRepository propertyRepo;


    public Amenity create(Amenity amenity, Long propertyId) {
        logger.info("Creating amenity '{}' for propertyId={}", amenity.getName(), propertyId);

        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> {
                    logger.error("Property not found with id={}", propertyId);
                    return new RuntimeException("Property not found");
                });

        amenity.setProperty(property);
        Amenity saved = amenityRepo.save(amenity);

        logger.info("Amenity created successfully with id={}", saved.getId());
        return saved;
    }

    public List<Amenity> getAll() {
        logger.info("Fetching all amenities");
        return amenityRepo.findAll();
    }

    public List<Amenity> getByProperty(Long propertyId) {
        logger.info("Fetching amenities for propertyId={}", propertyId);
        return amenityRepo.findByPropertyId(propertyId);
    }

    public Amenity getById(Long id) {
        logger.info("Fetching amenity with id={}", id);
        return amenityRepo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Amenity not found with id={}", id);
                    return new RuntimeException("Amenity not found");
                });
    }

    public Amenity update(Amenity amenity) {
        logger.info("Updating amenity with id={}", amenity.getId());
        return amenityRepo.save(amenity);
    }

    public void delete(Long id) {
        logger.info("Deleting amenity with id={}", id);
        amenityRepo.deleteById(id);
    }

	
	
}
