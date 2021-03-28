package com.medical.autism.trainer.model;

public class Schedule {
    public Integer id;
    public String from;
    public String to;
    public String day;

    public Schedule(Integer id, String from, String to, String day) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.day = day;
    }
}
