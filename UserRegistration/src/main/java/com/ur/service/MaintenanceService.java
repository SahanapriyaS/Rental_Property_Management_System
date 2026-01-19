package com.ur.service;

import java.util.List;

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

	@Autowired
    private MaintenanceRepository repo;
	
	@Autowired
    private PropertyRepository propertyRepo;


    public MaintenanceTicket create(MaintenanceTicket ticket, Long propertyId) {
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        ticket.setProperty(property);
        ticket.setStatus("OPEN");
        return repo.save(ticket);
    }

    public List<MaintenanceTicket> getAllTickets() {
        return repo.findAll();
    }

    public MaintenanceTicket getTicketById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public MaintenanceTicket updateStatus(Long id, String status) {
        MaintenanceTicket ticket = getTicketById(id);
        ticket.setStatus(status);
        return repo.save(ticket);
    }
}
