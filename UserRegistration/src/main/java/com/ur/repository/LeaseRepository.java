package com.ur.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ur.entity.Lease;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

    List<Lease> findByStatus(String status);

    List<Lease> findByTenantId(Long tenantId);

    List<Lease> findByPropertyId(Long propertyId);
}
