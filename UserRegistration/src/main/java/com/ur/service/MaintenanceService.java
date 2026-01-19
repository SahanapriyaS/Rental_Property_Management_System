package com.ur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ur.entity.MaintenanceTicket;
import com.ur.entity.Property;
import com.ur.repository.MaintenanceRepository;
import com.ur.repository.PropertyRepository;

@Service
@Transactional
public class MaintenanceService {

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceService.class);

    @Autowired
    private MaintenanceRepository repo;

    @Autowired
    private PropertyRepository propertyRepo;

    public MaintenanceTicket create(MaintenanceTicket ticket, Long propertyId) {
        logger.info("Creating maintenance ticket for propertyId={}", propertyId);

        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> {
                    logger.error("Property not found with id={}", propertyId);
                    return new RuntimeException("Property not found");
                });

        ticket.setProperty(property);
        ticket.setStatus("OPEN");

        MaintenanceTicket savedTicket = repo.save(ticket);
        logger.info("Maintenance ticket created successfully with id={}", savedTicket.getId());

        return savedTicket;
    }

    public List<MaintenanceTicket> getAllTickets() {
        logger.info("Fetching all maintenance tickets");
        return repo.findAll();
    }

    public MaintenanceTicket getTicketById(Long id) {
        logger.info("Fetching maintenance ticket with id={}", id);

        return repo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Maintenance ticket not found with id={}", id);
                    return new RuntimeException("Ticket not found");
                });
    }

    public MaintenanceTicket updateStatus(Long id, String status) {
        logger.info("Updating maintenance ticket status. ticketId={}, newStatus={}", id, status);

        MaintenanceTicket ticket = getTicketById(id);
        ticket.setStatus(status);

        MaintenanceTicket updatedTicket = repo.save(ticket);
        logger.info("Maintenance ticket status updated successfully for id={}", updatedTicket.getId());

        return updatedTicket;
    }
}
