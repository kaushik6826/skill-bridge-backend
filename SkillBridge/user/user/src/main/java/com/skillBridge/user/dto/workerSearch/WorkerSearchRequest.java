package com.skillBridge.user.dto.workerSearch;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WorkerSearchRequest {
	
	private Long categoryId;
    private Long skillId;
    
    @NotNull(message = "Page cannot be null")
    @Min(value = 0, message = "Page cannot be smaller than 0")
    private Integer page = 0;
    
    @NotNull(message = "Size cannot be null")
    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 200, message = "Size cannot be greater than 200")
    private Integer size = 10;

    private String sortBy = "id"; // rating / yearsOfExperience
    private String direction = "ASC";
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
    
    

}
