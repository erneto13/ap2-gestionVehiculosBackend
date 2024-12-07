package com.erneto13.sgfa_backend.dto;

public class DriverMinimalDto {
    private String name;
    private String profilePicture;

    public DriverMinimalDto() {}

    public DriverMinimalDto(String name, String profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}