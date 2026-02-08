package com.skillBridge.user.model.company;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "bulk_order_skill_req",schema = "companies")
public class BulkOrderSkillReq 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_order_id", nullable = false)
    private CompanyWorkOrder workOrder;

    private Integer skill_id;
    
    private Integer skill_level_id;
    private Integer required_count;
    private LocalDateTime createdAt;

    
    public BulkOrderSkillReq()
    {
    	
    }


	public Integer getSkill_id() {
		return skill_id;
	}


	public void setSkill_id(Integer skill_id) {
		this.skill_id = skill_id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public CompanyWorkOrder getWorkOrder() {
		return workOrder;
	}


	public void setWorkOrder(CompanyWorkOrder workOrder) {
		this.workOrder = workOrder;
	}




	public Integer getSkill_level_id() {
		return skill_level_id;
	}


	public void setSkill_level_id(Integer skill_level_id) {
		this.skill_level_id = skill_level_id;
	}


	public Integer getRequired_count() {
		return required_count;
	}


	public void setRequired_count(Integer required_count) {
		this.required_count = required_count;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    
    

}

