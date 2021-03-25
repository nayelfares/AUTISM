package com.medical.autism.onboarding.model;

public class Response {
    public Boolean success;
    public String message;
    public LoginData data;

    public Response(Boolean success, String message,LoginData data) {
        this.success = success;
        this.message = message;
        this.data    = data;
    }

}

