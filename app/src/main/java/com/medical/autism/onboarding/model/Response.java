package com.medical.autism.onboarding.model;

public class Response {
    public Boolean success;
    public String message;

    public Response(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
