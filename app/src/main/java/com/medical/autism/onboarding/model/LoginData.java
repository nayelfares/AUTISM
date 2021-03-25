package com.medical.autism.onboarding.model;

public class LoginData{
    public String token;
    public Integer id;
    public String type;

    public LoginData(String token, Integer id, String type) {
        this.token = token;
        this.id = id;
        this.type = type;
    }
}