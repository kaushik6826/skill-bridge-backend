package com.skillBridge.user.model.master;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "skill_category",schema = "master")
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Skill> skills;
    
    
   public  SkillCategory()
   {
	   
   }


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getCategoryName() {
	return categoryName;
}


public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public Boolean getIsActive() {
	return isActive;
}


public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
}


public LocalDateTime getCreatedAt() {
	return createdAt;
}


public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}


public List<Skill> getSkills() {
	return skills;
}


public void setSkills(List<Skill> skills) {
	this.skills = skills;
}
   
   

}