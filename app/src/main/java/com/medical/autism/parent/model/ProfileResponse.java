package com.medical.autism.parent.model;

public class ProfileResponse {
    public Boolean success;
    public String message;
    public ParentProfileData data;

    public ProfileResponse(Boolean success, String message, ParentProfileData data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
