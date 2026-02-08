package com.skillBridge.user.model.trans;

import com.skillBridge.user.model.company.CompanyWorkOrder;
import com.skillBridge.user.model.master.Worker;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "worker_assignment",schema = "trans")
public class WorkerAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Worker worker;

    @ManyToOne
    private CompanyWorkOrder workOrder;

    private Integer skill_id;

    private LocalDate assignedFrom;
    private LocalDate assignedTo;

    private String assignmentStatus; // ASSIGNED, COMPLETED, CANCELLED
    
    private LocalDateTime assignedAt;
    
    public WorkerAssignment()
    {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public CompanyWorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(CompanyWorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public Integer getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(Integer skill_id) {
		this.skill_id = skill_id;
	}

	public LocalDate getAssignedFrom() {
		return assignedFrom;
	}

	public void setAssignedFrom(LocalDate assignedFrom) {
		this.assignedFrom = assignedFrom;
	}

	public LocalDate getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(LocalDate assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}

	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}
    
    
    
}

