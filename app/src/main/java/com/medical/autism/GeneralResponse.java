package com.medical.autism;


public class GeneralResponse {
    public Boolean success;
    public String message;

    public GeneralResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
