package com.medical.autism.parent.model;

public class Appointment {
    public Integer appointment_id ;
    public String  time;
    public String  first_name;
    public String  last_name;

    public Appointment(Integer appointment_id, String time, String first_name, String last_name) {
        this.appointment_id = appointment_id;
        this.time = time;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
