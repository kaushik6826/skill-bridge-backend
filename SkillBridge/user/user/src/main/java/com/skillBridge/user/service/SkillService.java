package com.skillBridge.user.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.addSkill.AddSkillRequest;
import com.skillBridge.user.dto.addSkill.DeleteSkillRequest;
import com.skillBridge.user.dto.addSkill.GetSkillRequest;
import com.skillBridge.user.dto.addSkill.SkillResponse;
import com.skillBridge.user.dto.addSkill.UpdateSkillRequest;
import com.skillBridge.user.model.master.Skill;
import com.skillBridge.user.model.master.SkillCategory;
import com.skillBridge.user.repository.master.SkillCategoryRepository;
import com.skillBridge.user.repository.master.SkillRepository;

import jakarta.transaction.Transactional;

@Service
public class SkillService {
	private static final Logger LOGGER =
            LoggerFactory.getLogger(SkillService.class);

    private final SkillRepository skillRepository;
    private final SkillCategoryRepository categoryRepository;
    
    public SkillService(SkillRepository skillRepository,SkillCategoryRepository categoryRepository) {
    	this.skillRepository = skillRepository;
    	this.categoryRepository = categoryRepository;
    }
    
    @Transactional
    public CommonResponse addSkill(AddSkillRequest request) {

        CommonResponse response = new CommonResponse();

        try {

            String name = Optional.ofNullable(request.getSkillName())
                    .map(String::trim).orElse("");

            String desc = Optional.ofNullable(request.getDescription())
                    .map(String::trim).orElse("");

            if(name.isBlank() || request.getCategoryId() == null){
                response.setStatus("400");
                response.setMessage("Required fields missing");
                return response;
            }

            if(skillRepository.existsBySkillNameIgnoreCaseAndIsActiveTrue(name)){
                response.setStatus("200");
                response.setMessage("Skill already exists");
                return response;
            }

            SkillCategory category = categoryRepository
                    .findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            Skill skill = new Skill();
            skill.setSkillName(name);
            skill.setDescription(desc);
            skill.setCategory(category);

            Skill saved = skillRepository.save(skill);

            response.setStatus("200");
            response.setMessage("Skill added");
            response.setResult(saved);

        } catch (Exception ex) {
            LOGGER.error("Error adding skill", ex);
            response.setStatus("500");
            response.setMessage("Internal Error");
        }

        return response;
    }

    public CommonResponse getSkills(GetSkillRequest request) {

        CommonResponse response = new CommonResponse();

        try {

            List<SkillResponse> skills;

            if(request != null && request.getCategoryId() != null){
                skills = skillRepository
                        .findByCategoryIdAndIsActiveTrue(request.getCategoryId());
            }
            else{
                skills = skillRepository.findByIsActiveTrue();
            }

            response.setStatus("200");
            response.setMessage("Fetched");
            response.setResult(skills);

        } catch(Exception ex){
            LOGGER.error("Error fetching skills", ex);
            response.setStatus("500");
            response.setMessage("Internal Error");
        }

        return response;
    }

    @Transactional
    public CommonResponse updateSkill(UpdateSkillRequest request) {

        CommonResponse response = new CommonResponse();

        try {

            Skill skill = skillRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            String name = Optional.ofNullable(request.getSkillName())
                    .map(String::trim).orElse("");

            String desc = Optional.ofNullable(request.getDescription())
                    .map(String::trim).orElse("");

            if(!name.isBlank())
                skill.setSkillName(name);

            if(!desc.isBlank())
                skill.setDescription(desc);

            if(request.getCategoryId()!=null){
                SkillCategory cat = categoryRepository
                        .findById(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category missing"));
                skill.setCategory(cat);
            }

            Skill saved = skillRepository.save(skill);

            response.setStatus("200");
            response.setMessage("Updated");
            response.setResult(saved);

        } catch(Exception ex){
            LOGGER.error("Error updating skill", ex);
            response.setStatus("500");
            response.setMessage("Internal Error");
        }

        return response;
    }

    @Transactional
    public CommonResponse deleteSkill(DeleteSkillRequest request) {

        CommonResponse response = new CommonResponse();

        try {

            Skill skill = skillRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            skill.setIsActive(false);
            skillRepository.save(skill);

            response.setStatus("200");
            response.setMessage("Deleted");

        } catch(Exception ex){
            LOGGER.error("Error deleting skill", ex);
            response.setStatus("500");
            response.setMessage("Internal Error");
        }

        return response;
    }

}
