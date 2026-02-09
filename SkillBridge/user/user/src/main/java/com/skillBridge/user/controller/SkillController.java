package com.skillBridge.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.addSkill.AddSkillRequest;
import com.skillBridge.user.dto.addSkill.DeleteSkillRequest;
import com.skillBridge.user.dto.addSkill.GetSkillRequest;
import com.skillBridge.user.dto.addSkill.UpdateSkillRequest;

import com.skillBridge.user.service.SkillService;

@RestController
@RequestMapping("/master/skills")
public class SkillController {

	private static final Logger LOGGER =
            LoggerFactory.getLogger(SkillController.class);

    private final SkillService skillService;
    
    public SkillController(SkillService skillService) {
    	this.skillService = skillService;
    }
    
    @PostMapping("/add")
    public CommonResponse add(@RequestBody AddSkillRequest req){
        LOGGER.info("Add Skill API called");
        return skillService.addSkill(req);
    }

    @PostMapping("/list")
    public CommonResponse get(@RequestBody(required=false) GetSkillRequest req){
        LOGGER.info("Get Skills API called");
        return skillService.getSkills(req);
    }

    @PostMapping("/update")
    public CommonResponse update(@RequestBody UpdateSkillRequest req){
        LOGGER.info("Update Skill API called");
        return skillService.updateSkill(req);
    }

    @PostMapping("/delete")
    public CommonResponse delete(@RequestBody DeleteSkillRequest req){
        LOGGER.info("Delete Skill API called");
        return skillService.deleteSkill(req);
    }

}
