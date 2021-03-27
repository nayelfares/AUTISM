package com.medical.autism.parent.model;

public class Parent {
    public Integer id;
    public String  user_temp_name;
    public String  email;
    public String  photo;
    public String  gender;
    public String  type;
    public String  dob;
    public String  details;
    public String  phone;
    public String  first_name;
    public String  last_name;
    public String  child_name;
    public String  child_age;
    public String  parent_job;
    public String  marriage_status;
    public String  parent_gender;
    public String  child_number;
    public String  child_main_problem;
    public Appointment[] appointment;

    public Parent(
            Integer id, String user_temp_name, String email, String photo, String gender, String type,
            String dob, String details, String phone, String first_name, String last_name, String child_name,
            String child_age, String parent_job, String marriage_status, String parent_gender, String child_number,
            String child_main_problem, Appointment[] appointment
    ) {
        this.id = id;
        this.user_temp_name = user_temp_name;
        this.email = email;
        this.photo = photo;
        this.gender = gender;
        this.type = type;
        this.dob = dob;
        this.details = details;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.child_name = child_name;
        this.child_age = child_age;
        this.parent_job = parent_job;
        this.marriage_status = marriage_status;
        this.parent_gender = parent_gender;
        this.child_number = child_number;
        this.child_main_problem = child_main_problem;
        this.appointment = appointment;
    }
}
