package com.skillBridge.user.dto.addLabour;

import java.util.List;

public class AddLabourSkillsRequestDTO {
    private Long labourId;
    private List<Long> skillIds;
    public Long getLabourId() {
        return labourId;

    }
    public void setLabourId(Long labourId) {
        this.labourId = labourId;
    }
    public List<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Long> skillIds) {
        this.skillIds = skillIds;
    }

    public void  addSkillId(Long skillId) {
        this.skillIds.add(skillId);
    }
}
