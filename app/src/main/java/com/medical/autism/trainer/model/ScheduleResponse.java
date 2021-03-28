package com.medical.autism.trainer.model;

import java.util.ArrayList;

public class ScheduleResponse {
    public Boolean success;
    public String message;
    public ArrayList<Schedule> data;

    public ScheduleResponse(Boolean success, String message, ArrayList<Schedule> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
