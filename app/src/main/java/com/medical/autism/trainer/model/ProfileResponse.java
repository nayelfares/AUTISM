package com.medical.autism.trainer.model;

public class ProfileResponse {
    public Boolean success;
    public String message;
    public TrainerProfileData data;

    public ProfileResponse(Boolean success, String message, TrainerProfileData data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
