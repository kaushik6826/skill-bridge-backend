package com.skillBridge.user.model.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "worker_visa_medical",schema = "master")
public class WorkerVisaMedical {

    @Id
    private Long workerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private String destinationCountry;
    private String visaType;
    private String medicalStatus;

    
    public WorkerVisaMedical()
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


	public String getDestinationCountry() {
		return destinationCountry;
	}


	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}


	public String getVisaType() {
		return visaType;
	}


	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}


	public String getMedicalStatus() {
		return medicalStatus;
	}


	public void setMedicalStatus(String medicalStatus) {
		this.medicalStatus = medicalStatus;
	}
    
    


}