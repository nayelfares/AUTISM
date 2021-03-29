package com.medical.autism.trainer.api;

import com.medical.autism.GeneralResponse;
import com.medical.autism.trainer.model.ProfileResponse;
import com.medical.autism.trainer.model.ScheduleResponse;
import com.medical.autism.trainer.model.TrainerAppointmentResponse;
import com.medical.autism.trainer.model.TrainerPatientResponse;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrainerService {

    @GET("user-profile_get")
    Observable<ProfileResponse> getProfile(
            @Header("Authorization")String token,
            @Query("id") Integer id
    );

    @POST("change_photo")
    @Multipart
    Observable<GeneralResponse> upload(
            @Header("Authorization")String token,
            @Part MultipartBody.Part photo,
            @Query("id") int id
    );

    @POST("user-profile_update")
    Observable<GeneralResponse> updateProfile(
            @Header("Authorization")String token,
            @Query("id") int id,
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


    @GET("schedule")
    Observable<ScheduleResponse> getSchedules(
            @Header("Authorization")String token,
            @Query("id") Integer id
    );

    @POST("schedule")
    Observable<GeneralResponse> addSchedule(
            @Header("Authorization")String token,
            @Query("id") Integer id,
            @Query("date")String date,
            @Query("from")String from,
            @Query("to")String to
    );

    @GET("appointment_trainer")
    Observable<TrainerAppointmentResponse> getAppointments(
            @Header("Authorization")String token,
            @Query("trainer_id") Integer trainer_id,
            @Query("day")String day
    );

    @DELETE("appointment/{id}")
    Observable<GeneralResponse> deleteAppointment(
            @Header("Authorization")String token,
            @Path("id") Integer id
    );

    @GET("parent")
    Observable<TrainerPatientResponse> getPatients(
            @Header("Authorization")String token,
            @Query("trainer_id") Integer trainer_id
    );


    @POST("update_main_problem")
    Observable<GeneralResponse> updateMainProblem(
            @Header("Authorization")String token,
            @Query("id") String id,
            @Query("child_main_problem")String child_main_problem
    );

    @GET("get_parent")
    Observable<TrainerPatientResponse> getParents(
            @Header("Authorization")String token
    );
}
