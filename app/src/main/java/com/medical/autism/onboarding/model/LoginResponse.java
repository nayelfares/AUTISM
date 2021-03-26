package com.medical.autism.onboarding.model;

public class LoginResponse {
    public Boolean success;
    public String message;
    public LoginData data;

    public LoginResponse(Boolean success, String message, LoginData data) {
        this.success = success;
        this.message = message;
        this.data    = data;
    }
}

