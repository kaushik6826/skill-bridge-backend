package com.skillBridge.user.dto.addLabour;

public class AddLabourResponseDTO {

    private Long labourId;
    private String firstName;
    private String lastName;
    private String message;

    public AddLabourResponseDTO(Long labourId, String firstName, String lastName, String message) {
        this.labourId = labourId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
    }

    // Getters

    public Long getLabourId() {
        return labourId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMessage() {
        return message;
    }
}

