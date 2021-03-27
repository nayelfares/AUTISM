package com.medical.autism.trainer.model;


public class Trainer {
    public Integer id;
    public String  user_name;
    public String  first_name;
    public String  last_name;
    public String  email;
    public String  photo;
    public String  phone;
    public String speciality;
    public String collage;
    public String previous_clinics;
    public String clinic_address;
    public String experience_years;
    public String certificate_number;

    public Trainer(Integer id, String user_name, String first_name, String last_name, String email, String photo,
                   String phone, String speciality, String collage, String previous_clinics, String clinic_address,
                   String experience_years, String certificate_number) {
        this.id = id;
        this.user_name = user_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.photo = photo;
        this.phone = phone;
        this.speciality = speciality;
        this.collage = collage;
        this.previous_clinics = previous_clinics;
        this.clinic_address = clinic_address;
        this.experience_years = experience_years;
        this.certificate_number = certificate_number;
    }
}
