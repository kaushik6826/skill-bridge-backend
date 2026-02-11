package com.skillBridge.user.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillBridge.user.model.master.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

	Optional<Worker> findByPhone(String phone);}
