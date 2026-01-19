package com.ur.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ur.entity.MaintenanceTicket;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceTicket, Long> {

    List<MaintenanceTicket> findByStatus(String status);

    List<MaintenanceTicket> findByPropertyId(Long propertyId);
}
