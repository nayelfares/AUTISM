package com.medical.autism.trainer.ui;

import com.medical.autism.trainer.model.Schedule;

import java.util.ArrayList;

public interface SchedualesView {
    void getSchedulesSuccess(ArrayList<Schedule> data);

    void getSchedulesFailed(String message);
}
