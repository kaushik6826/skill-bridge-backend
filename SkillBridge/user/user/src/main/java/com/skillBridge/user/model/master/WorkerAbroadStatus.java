package com.skillBridge.user.model.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "worker_abroad_status",schema = "master")
public class WorkerAbroadStatus {

    @Id
    private Long workerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "worker_id")
    private Worker worker;
    private String abroadStatus;
    
    public WorkerAbroadStatus()
    {
    	
    }

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String getAbroadStatus() {
		return abroadStatus;
	}

	public void setAbroadStatus(String abroadStatus) {
		this.abroadStatus = abroadStatus;
	}
    
    

   
}