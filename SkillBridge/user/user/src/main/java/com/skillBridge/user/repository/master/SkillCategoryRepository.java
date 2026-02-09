package com.skillBridge.user.repository.master;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillBridge.user.dto.skillCategory.AddSkillCategoryResponse;
import com.skillBridge.user.model.master.SkillCategory;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory,Long>{
	
	Boolean existsByCategoryNameIgnoreCaseAndIsActiveTrue(String categoryName);

    Boolean existsByCategoryName(String categoryName);
	
	Optional<AddSkillCategoryResponse> findByCategoryNameIgnoreCaseAndIsActiveTrue(String categoryName);

	Optional<AddSkillCategoryResponse> findByCategoryNameIgnoreCase(String categoryName);

	List<AddSkillCategoryResponse> findByIsActiveTrue();


}
