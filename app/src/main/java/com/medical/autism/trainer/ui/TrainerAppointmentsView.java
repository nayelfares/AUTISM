package com.medical.autism.trainer.ui;

import com.medical.autism.trainer.model.TrainerAppointment;

import java.util.ArrayList;

public interface TrainerAppointmentsView {
    void getAppointmentsSuccess(ArrayList<TrainerAppointment> data);

    void getAppointmentsFailed(String message);
}
