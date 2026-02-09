package com.skillBridge.user.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillBridge.user.dto.addSkill.SkillResponse;
import com.skillBridge.user.model.master.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long>{
	
	Boolean existsBySkillNameIgnoreCaseAndIsActiveTrue(String name);

    List<SkillResponse> findByIsActiveTrue();

    List<SkillResponse> findByCategoryIdAndIsActiveTrue(Long categoryId);
    

}
