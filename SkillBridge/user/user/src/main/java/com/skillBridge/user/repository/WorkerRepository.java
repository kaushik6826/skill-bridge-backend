package com.skillBridge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillBridge.user.model.master.Worker;


@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> 
{



}
