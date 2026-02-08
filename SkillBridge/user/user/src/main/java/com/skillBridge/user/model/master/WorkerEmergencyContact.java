package com.skillBridge.user.model.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "worker_emergency_contacts",schema = "master")
public class WorkerEmergencyContact {

    @Id
    private Long workerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private String contactName;
    private String relation;
    private String contactNumber;

    @Column(columnDefinition = "TEXT")
    private String internalNotes;
    
    
    public WorkerEmergencyContact()
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


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getRelation() {
		return relation;
	}


	public void setRelation(String relation) {
		this.relation = relation;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getInternalNotes() {
		return internalNotes;
	}


	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}
    
    
    
    
    
}