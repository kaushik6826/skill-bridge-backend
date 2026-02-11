package com.skillBridge.user.model.master;

import java.time.LocalDateTime;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "workers",schema = "master")
public class Worker
{

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    @Column(unique = true)
    private String workerCode;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private Integer age;
    private Integer yearsOfExperience;
    private Float rating;
    private Boolean isActive;
    private Boolean isAvailable;
    private Boolean isCompanyAssigned;
    private String password;
    private String gender;
    private String city;
    private Integer totalJobsCompleted;
    private Integer totalJobsCancelled;
    private LocalDateTime lastJobCompletedAt;
    private Boolean isBlocked;
    private String blockReason;
    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;
    private LocalDateTime lastLoginAt;
    private Integer failedLoginAttempts;
    private Boolean isAccountLocked;
    
    
    public class Builder {

		public Object email(String email) {
			// TODO Auto-generated method stub
			return null;
		}

	}
    
	/*
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval =
	 * true)
	 * 
	 * @JoinColumn(name = "doc_details_id", nullable = false) private
	 * WorkerDocuments workerDocuments;
	 * 
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval =
	 * true)
	 * 
	 * @JoinColumn(name = "bank_details_id", nullable = false) private BankDetails
	 * bankDetails;
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWorkerCode() {
		return workerCode;
	}
	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Boolean getIsCompanyAssigned() {
		return isCompanyAssigned;
	}
	public void setIsCompanyAssigned(Boolean isCompanyAssigned) {
		this.isCompanyAssigned = isCompanyAssigned;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getTotalJobsCompleted() {
		return totalJobsCompleted;
	}
	public void setTotalJobsCompleted(Integer totalJobsCompleted) {
		this.totalJobsCompleted = totalJobsCompleted;
	}
	public Integer getTotalJobsCancelled() {
		return totalJobsCancelled;
	}
	public void setTotalJobsCancelled(Integer totalJobsCancelled) {
		this.totalJobsCancelled = totalJobsCancelled;
	}
	public LocalDateTime getLastJobCompletedAt() {
		return lastJobCompletedAt;
	}
	public void setLastJobCompletedAt(LocalDateTime lastJobCompletedAt) {
		this.lastJobCompletedAt = lastJobCompletedAt;
	}
	public Boolean getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public String getBlockReason() {
		return blockReason;
	}
	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}
	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}
	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	public Boolean getIsPhoneVerified() {
		return isPhoneVerified;
	}
	public void setIsPhoneVerified(Boolean isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}
	public LocalDateTime getLastLoginAt() {
		return lastLoginAt;
	}
	public void setLastLoginAt(LocalDateTime lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}
	public Integer getFailedLoginAttempts() {
		return failedLoginAttempts;
	}
	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}
	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}
	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

      
    
    
    
}