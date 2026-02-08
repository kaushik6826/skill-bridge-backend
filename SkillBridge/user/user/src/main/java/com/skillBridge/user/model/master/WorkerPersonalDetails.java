package com.skillBridge.user.model.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "worker_personal_details",schema = "master")
public class WorkerPersonalDetails {

    @Id
    private Long workerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private Integer age;
    private String gender;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String aadhaarNo;
    private String highestQualification;
    private String disability;
    
    
    public WorkerPersonalDetails()
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


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getAadhaarNo() {
		return aadhaarNo;
	}


	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}


	public String getHighestQualification() {
		return highestQualification;
	}


	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}


	public String getDisability() {
		return disability;
	}


	public void setDisability(String disability) {
		this.disability = disability;
	}
    
    
    
    

}