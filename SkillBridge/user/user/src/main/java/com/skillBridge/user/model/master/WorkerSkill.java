package com.skillBridge.user.model.master;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "worker_skill", schema = "master", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "worker_id", "skill_id" }) })
public class WorkerSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "worker_id", nullable = false)
	private Worker worker;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_id", nullable = false)
	private Skill skill;

	@Column(name = "skill_level")
	private String skillLevel; // BEGINNER / INTERMEDIATE / EXPERT

	@Column(name = "experience_years")
	private Integer experienceYears;

	@Column(name = "is_verified")
	private Boolean isVerified = false;

	@Column(name = "assigned_at")
	private LocalDateTime assignedAt = LocalDateTime.now();

	public WorkerSkill() {

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

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public Integer getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}

	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}

}