package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.trainer.ui.TrainerAppointmentsView;

public class TrainerAppointmentsViewModel {
    Context context;
    TrainerAppointmentsView trainerAppointmentsView;

    public TrainerAppointmentsViewModel(Context context, TrainerAppointmentsView trainerAppointmentsView) {
        this.context = context;
        this.trainerAppointmentsView = trainerAppointmentsView;
    }

    public void getAppointments() {
    }
}
