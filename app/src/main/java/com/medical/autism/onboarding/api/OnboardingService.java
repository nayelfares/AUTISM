package com.medical.autism.onboarding.api;


import com.medical.autism.onboarding.model.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OnboardingService {
    @POST("user-login")
    Observable<LoginResponse> login(
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("user-register")
    Observable<LoginResponse> register(
            @Query("first_name") String first_name,
            @Query("last_name") String last_name,
            @Query("user_name") String user_name,
            @Query("email") String email,
            @Query("password") String password,
            @Query("phone") String phone,
            @Query("type") String type,
            @Query("speciality") String speciality,
            @Query("collage") String collage,
            @Query("previous_clincs") String previous_clincs,
            @Query("clinic_address") String clinic_address,
            @Query("experience_years") String experience_years,
            @Query("certificate_number") String certificate_number,
            @Query("child_name") String child_name,
            @Query("child_age") String child_age,
            @Query("parent_job") String parent_job,
            @Query("marriage_status") String marriage_status,
            @Query("parent_gender") String parent_gender,
            @Query("child_number") String child_number,
            @Query("child_main_problem") String child_main_problem
    );

}
