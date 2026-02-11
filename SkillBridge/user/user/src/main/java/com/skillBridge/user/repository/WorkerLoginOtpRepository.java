package com.skillBridge.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillBridge.user.model.WorkerLoginOtp;

@Repository
public interface WorkerLoginOtpRepository extends JpaRepository<WorkerLoginOtp, Long> {

	Optional<WorkerLoginOtp> findTopByMobilenoAndVerifiedFalseOrderByCreatedAtDesc(int mobileno);
}
