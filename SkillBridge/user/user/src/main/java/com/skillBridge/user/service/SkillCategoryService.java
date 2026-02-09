package com.skillBridge.user.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.skillCategory.AddSkillCategoryRequest;
import com.skillBridge.user.dto.skillCategory.AddSkillCategoryResponse;
import com.skillBridge.user.dto.skillCategory.DeleteSkillCategory;
import com.skillBridge.user.dto.skillCategory.UpdateSkillCategoryRequest;
import com.skillBridge.user.model.master.SkillCategory;
import com.skillBridge.user.repository.master.SkillCategoryRepository;
import com.skillBridge.user.repository.master.SkillRepository;

import jakarta.transaction.Transactional;

@Service
public class SkillCategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SkillCategoryService.class);
 
	private final SkillCategoryRepository skillCategoryRepository;
	
	public SkillCategoryService(SkillCategoryRepository skillCategoryRepository){
		this.skillCategoryRepository = skillCategoryRepository;
	}
	
	@Transactional
	public CommonResponse addSkillCategory(AddSkillCategoryRequest request) {
	
	    CommonResponse response = new CommonResponse();
	
	    try {
	
	        if (request == null) {
	            response.setStatus("400");
	            response.setMessage("Request cannot be null");
	            return response;
	        }
	
	        String categoryName = Optional.ofNullable(request.getCategoryName())
	                .map(String::trim)
	                .orElse("");
	
	        String description = Optional.ofNullable(request.getDescription())
	                .map(String::trim)
	                .orElse("");
	
	        if (categoryName.isBlank() || description.isBlank()) {
	            response.setStatus("400");
	            response.setMessage("Required parameters missing");
	            return response;
	        }
	
	        // ---------- Exists & Active ----------
	        if (skillCategoryRepository
	                .existsByCategoryNameIgnoreCaseAndIsActiveTrue(categoryName)) {
	
	            response.setStatus("200");
	            response.setMessage("Skill Category already exists");
	
	            response.setResult(
	                skillCategoryRepository
	                    .findByCategoryNameIgnoreCaseAndIsActiveTrue(categoryName)
	                    .orElse(null)
	            );
	            return response;
	        }
	
	        // ---------- Exists but inactive ----------
	        if (skillCategoryRepository.existsByCategoryName(categoryName)) {
	
	            SkillCategory entity =
	                    skillCategoryRepository
	                        .findByCategoryNameIgnoreCase(categoryName)
	                        .map(p -> skillCategoryRepository.findById(p.getId()).orElse(null))
	                        .orElse(null);
	
	            if (entity != null) {
	                entity.setIsActive(true);
	                entity.setDescription(description);
	                entity.setCreatedAt(LocalDateTime.now());
	                skillCategoryRepository.save(entity);
	            }
	
	            response.setStatus("200");
	            response.setMessage("Category reactivated");
	            response.setResult(
	                skillCategoryRepository
	                    .findByCategoryNameIgnoreCaseAndIsActiveTrue(categoryName)
	                    .orElse(null)
	            );
	            return response;
	        }
	
	        // ---------- Create ----------
	        SkillCategory category = new SkillCategory();
	        category.setCategoryName(categoryName);
	        category.setDescription(description);
	        category.setIsActive(true);
	        category.setCreatedAt(LocalDateTime.now());
	
	        skillCategoryRepository.save(category);
	
	        response.setStatus("200");
	        response.setMessage("Category added");
	        response.setResult(
	            skillCategoryRepository
	                .findByCategoryNameIgnoreCaseAndIsActiveTrue(categoryName)
	                .orElse(null)
	        );
	
	    } catch (Exception ex) {
	        LOGGER.error("Error adding category", ex);
	        response.setStatus("500");
	        response.setMessage("Internal server error");
	    }
	
	    return response;
	}

	
	public CommonResponse getSkillCategory() {

	    CommonResponse response = new CommonResponse();
	
	    try {
	
	        List<AddSkillCategoryResponse> list =
	                skillCategoryRepository.findByIsActiveTrue();
	
	        response.setStatus("200");
	        response.setMessage("Fetched");
	        response.setResult(list);
	
	    } catch (Exception ex) {
	        LOGGER.error("Fetch failed", ex);
	        response.setStatus("500");
	        response.setMessage("Internal server error");
	    }
	
	    return response;
	}


	@Transactional
	public CommonResponse updateSkillCategory(UpdateSkillCategoryRequest request) {
	
	    CommonResponse response = new CommonResponse();
	
	    try {
	
	        if (request == null || request.getCategoryId() == null) {
	            response.setStatus("400");
	            response.setMessage("Category ID required");
	            return response;
	        }
	
	        SkillCategory entity =
	                skillCategoryRepository.findById(request.getCategoryId())
	                        .orElse(null);
	
	        if (entity == null) {
	            response.setStatus("404");
	            response.setMessage("Category not found");
	            return response;
	        }
	
	        String name = Optional.ofNullable(request.getCategoryName())
	                .map(String::trim)
	                .orElse("");
	
	        String desc = Optional.ofNullable(request.getDescription())
	                .map(String::trim)
	                .orElse("");
	
	        if (!name.isBlank()) {
	
	            boolean exists =
	                skillCategoryRepository
	                    .existsByCategoryNameIgnoreCaseAndIsActiveTrue(name);
	
	            if (exists &&
	                !entity.getCategoryName().equalsIgnoreCase(name)) {
	
	                response.setStatus("409");
	                response.setMessage("Duplicate category name");
	                return response;
	            }
	
	            entity.setCategoryName(name);
	        }
	
	        if (!desc.isBlank())
	            entity.setDescription(desc);
	
	        skillCategoryRepository.save(entity);
	
	        response.setStatus("200");
	        response.setMessage("Updated");
	        response.setResult(
	            skillCategoryRepository
	                .findByCategoryNameIgnoreCaseAndIsActiveTrue(entity.getCategoryName())
	                .orElse(null)
	        );
	
	    } catch (Exception ex) {
	        LOGGER.error("Update failed", ex);
	        response.setStatus("500");
	        response.setMessage("Internal server error");
	    }
	
	    return response;
	}


	@Transactional
	public CommonResponse deleteSkillCategory(DeleteSkillCategory request) {
	
	    CommonResponse response = new CommonResponse();
	
	    try {
	
	        SkillCategory entity =
	                skillCategoryRepository.findById(request.getId())
	                        .orElse(null);
	
	        if (entity == null) {
	            response.setStatus("404");
	            response.setMessage("Not found");
	            return response;
	        }
	
	        entity.setIsActive(false);
	        skillCategoryRepository.save(entity);
	
	        response.setStatus("200");
	        response.setMessage("Deleted");
	
	    } catch (Exception ex) {
	        LOGGER.error("Delete failed", ex);
	        response.setStatus("500");
	        response.setMessage("Internal server error");
	    }
	
	    return response;
	}


	
}
