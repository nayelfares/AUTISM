package com.medical.autism.trainer.model;

import java.util.ArrayList;

public class TrainerAppointmentResponse {
    public Boolean success;
    public String message;
    public ArrayList<TrainerAppointment> data;

    public TrainerAppointmentResponse(Boolean success, String message, ArrayList<TrainerAppointment> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
