package com.medical.autism.trainer.model;

public class TrainerPatient {
    public String user_id;
    public String first_name;
    public String child_name;
    public String last_name;
    public String child_age;
    public String parent_job;
    public String marriage_status;
    public String parent_gender;
    public String child_number;
    public String child_main_problem;
    public String time;
    public String day;
    public String photo;

    public TrainerPatient(
            String user_id, String first_name, String child_name, String last_name, String child_age,
            String parent_job, String marriage_status, String parent_gender, String child_number,
            String child_main_problem, String time, String day, String photo
    ) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.child_name = child_name;
        this.last_name = last_name;
        this.child_age = child_age;
        this.parent_job = parent_job;
        this.marriage_status = marriage_status;
        this.parent_gender = parent_gender;
        this.child_number = child_number;
        this.child_main_problem = child_main_problem;
        this.time = time;
        this.day = day;
        this.photo = photo;
    }
}

