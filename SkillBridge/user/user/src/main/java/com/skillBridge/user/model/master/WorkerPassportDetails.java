package com.skillBridge.user.model.master;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "worker_passport_details",schema = "master")
public class WorkerPassportDetails {

    @Id
    private Long workerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private String passportNumber;
    private String placeOfIssue;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private Boolean hasPassport;
    
    
    public WorkerPassportDetails()
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


	public String getPassportNumber() {
		return passportNumber;
	}


	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}


	public String getPlaceOfIssue() {
		return placeOfIssue;
	}


	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}


	public LocalDate getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}


	public LocalDate getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}


	public Boolean getHasPassport() {
		return hasPassport;
	}


	public void setHasPassport(Boolean hasPassport) {
		this.hasPassport = hasPassport;
	}
    
    
    
    
    
    
    
}