package com.skillBridge.user.dto.workerSearch;

public class WorkerSearchResponse {
	private Long id;
    private String workerCode;
    private String firstName;
    private String lastName;
    private Float rating;
    private Integer yearsOfExperience;
	public WorkerSearchResponse() {
	}
	
	public WorkerSearchResponse(Long id, String workerCode, String firstName, String lastName, Float rating,
			Integer yearsOfExperience) {
		super();
		this.id = id;
		this.workerCode = workerCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
		this.yearsOfExperience = yearsOfExperience;
	}

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
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
    
}
