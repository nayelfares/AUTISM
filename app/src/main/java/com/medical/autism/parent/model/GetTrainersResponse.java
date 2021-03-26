package com.medical.autism.parent.model;


public class GetTrainersResponse {
    public Boolean success;
    public String message;
    public Trainer[] data;

    public GetTrainersResponse(Boolean success, String message,Trainer[] data) {
        this.success = success;
        this.message = message;
        this.data    = data;
    }

}
