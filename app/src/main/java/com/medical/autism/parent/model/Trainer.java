package com.medical.autism.parent.model;

public class Trainer {
    public Integer id;
    public String  user_name;
    public String  email;
    public String  photo;
    public String  gender;
    public String  type;
    public String  dob;
    public String  details;
    public String  phone;
    public String  first_name;
    public String  last_name;
    public String  speciality;
    public String  collage;
    public String  language;
    public String  clinic_address;
    public String  previous_clinics;
    public String  avaliable_time;
    public String  experience_years;
    public String  certificate_number;

    public Trainer(Integer id, String user_name, String email, String photo, String gender,
                   String type, String dob, String details, String phone, String first_name,
                   String last_name, String speciality, String collage, String language,
                   String clinic_address, String previous_clinics, String avaliable_time,
                   String experience_years, String certificate_number
    ) {
        this.id = id;
        this.user_name = user_name;
        this.email = email;
        this.photo = photo;
        this.gender = gender;
        this.type = type;
        this.dob = dob;
        this.details = details;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.speciality = speciality;
        this.collage = collage;
        this.language = language;
        this.clinic_address = clinic_address;
        this.previous_clinics = previous_clinics;
        this.avaliable_time = avaliable_time;
        this.experience_years = experience_years;
        this.certificate_number = certificate_number;
    }
}
