package com.medical.autism.parent.model;

public class Period {
    public Integer schedule_id ;
    public String time;
    public String end_time;
    public Boolean booked;
    public Boolean selected = false;

    public Period(String time, Boolean booked) {
        this.time = time;
        this.booked = booked;
    }
}
