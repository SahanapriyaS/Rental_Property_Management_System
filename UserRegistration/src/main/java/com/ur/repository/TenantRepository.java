package com.ur.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ur.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    List<Tenant> findByVerified(boolean verified);

    List<Tenant> findByPropertyId(Long propertyId);
}
