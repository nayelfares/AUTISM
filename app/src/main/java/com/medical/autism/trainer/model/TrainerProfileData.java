package com.medical.autism.trainer.model;

import com.medical.autism.parent.model.Appointment;

import java.util.ArrayList;

public class TrainerProfileData {
    public Trainer user;
    public ArrayList<Appointment> appointment;

    public TrainerProfileData(Trainer user, ArrayList<Appointment> appointment) {
        this.user = user;
        this.appointment = appointment;
    }
}
