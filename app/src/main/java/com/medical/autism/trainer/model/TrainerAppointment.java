package com.medical.autism.trainer.model;

public class TrainerAppointment {
    public String time;
    public String parent;
    public String child;
    public int appointment_id;

    public TrainerAppointment(String time, String parent, String child, int appointment_id) {
        this.time = time;
        this.parent = parent;
        this.child = child;
        this.appointment_id = appointment_id;
    }
}
