package com.medical.autism.trainer.model;

import java.util.ArrayList;

public class TrainerPatientResponse {
    public Boolean success;
    public String message;
    public ArrayList<TrainerPatient> data;

    public TrainerPatientResponse(Boolean success, String message, ArrayList<TrainerPatient> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
