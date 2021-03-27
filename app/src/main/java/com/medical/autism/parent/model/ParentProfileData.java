package com.medical.autism.parent.model;

import java.util.ArrayList;

public class ParentProfileData {
    public Parent user;
    public ArrayList<Appointment> appointment;

    public ParentProfileData(Parent user, ArrayList<Appointment> appointment) {
        this.user = user;
        this.appointment = appointment;
    }
}
