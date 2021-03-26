package com.medical.autism.parent.model;

public class PeriodsResponse {
    public Boolean success;
    public String message;
    public Period[] data;

    public PeriodsResponse(Boolean success, String message,Period[] data) {
        this.success = success;
        this.message = message;
        this.data    = data;
    }

}
