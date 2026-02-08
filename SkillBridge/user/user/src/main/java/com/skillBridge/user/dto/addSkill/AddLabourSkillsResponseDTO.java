package com.skillBridge.user.dto.addSkill;

import java.util.List;

public class AddLabourSkillsResponseDTO {
    private Long labourId;
    private List<Long> addedSkillIds;

    public AddLabourSkillsResponseDTO(Long labourId,
                                      List<Long> addedSkillIds) {
        this.labourId = labourId;
        this.addedSkillIds = addedSkillIds;
    }

    public Long getLabourId() {
        return labourId;
    }

    public void setLabourId(Long labourId) {
        this.labourId = labourId;
    }
    public List<Long> getAddedSkillIds() {
        return addedSkillIds;
    }
    public void setAddedSkillIds(List<Long> addedSkillIds) {
        this.addedSkillIds = addedSkillIds;
    }
}
