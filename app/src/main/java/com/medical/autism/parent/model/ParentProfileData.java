package com.medical.autism.parent.model;

public class ParentProfileData {
    public Parent user;
    public Appointment[] appointment;

    public ParentProfileData(Parent user, Appointment[] appointment) {
        this.user = user;
        this.appointment = appointment;
    }
}
