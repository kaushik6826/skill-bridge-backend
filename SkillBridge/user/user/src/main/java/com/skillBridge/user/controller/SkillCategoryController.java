package com.skillBridge.user.controller;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.skillCategory.AddSkillCategoryRequest;
import com.skillBridge.user.dto.skillCategory.DeleteSkillCategory;
import com.skillBridge.user.dto.skillCategory.UpdateSkillCategoryRequest;
import com.skillBridge.user.service.SkillCategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/master/skill-category")
public class SkillCategoryController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SkillCategoryController.class);

    @Autowired
    private SkillCategoryService skillCategoryService;

    // =========================================================
    // ADD CATEGORY
    // =========================================================
    @PostMapping("/add")
    public CommonResponse addSkillCategory(
            @RequestBody AddSkillCategoryRequest request) {

        LOGGER.info("API call -> Add Skill Category");

        return skillCategoryService.addSkillCategory(request);
    }

    // =========================================================
    // GET ALL ACTIVE CATEGORIES
    // =========================================================
    @GetMapping("/list")
    public CommonResponse getSkillCategories() {

        LOGGER.info("API call -> Get Skill Categories");

        return skillCategoryService.getSkillCategory();
    }

    // =========================================================
    // UPDATE CATEGORY
    // =========================================================
    @PostMapping("/update")
    public CommonResponse updateSkillCategory(
            @RequestBody UpdateSkillCategoryRequest  request) {

        LOGGER.info("API call -> Update Skill Category");

        return skillCategoryService.updateSkillCategory(request);
    }

    // =========================================================
    // SOFT DELETE CATEGORY
    // =========================================================
    @PostMapping("/delete")
    public CommonResponse deleteSkillCategory(
            @RequestBody DeleteSkillCategory request) {

        LOGGER.info("API call -> Delete Skill Category");

        return skillCategoryService.deleteSkillCategory(request);
    }
}

