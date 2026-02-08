package com.skillBridge.user.model.company;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "company_work_order",schema = "companies")
public class CompanyWorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_order_id")
    private Long workOrderId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String workOrderNumber;
    private String workLocation;
    private String city;
    private String state;
    private String zipCode;
    private Integer requiredCount;
    private String status;   // CREATED, ASSIGNED, IN_PROGRESS, COMPLETED
    private LocalDateTime createdAt;
    private LocalDateTime deleviredAt;


    public CompanyWorkOrder()
    {
    	
    }
    
    
    
    

    public LocalDateTime getDeleviredAt() {
		return deleviredAt;
	}





	public void setDeleviredAt(LocalDateTime deleviredAt) {
		this.deleviredAt = deleviredAt;
	}





	public Integer getRequiredCount() {
		return requiredCount;
	}



	public void setRequiredCount(Integer requiredCount) {
		this.requiredCount = requiredCount;
	}



	public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    public void setWorkOrderNumber(String workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

