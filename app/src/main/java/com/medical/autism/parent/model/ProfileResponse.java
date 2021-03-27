package com.medical.autism.parent.model;

public class ProfileResponse {
    public Boolean success;
    public String message;
    public Parent data;

    public ProfileResponse(Boolean success, String message, Parent data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
