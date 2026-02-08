package com.skillBridge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillBridge.user.model.company.CompanyWorkOrder;

@Repository
public interface CompanyWorkOrderRepository extends JpaRepository<CompanyWorkOrder, Long> {
}
