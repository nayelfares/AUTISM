package com.medical.autism.parent.api;

import com.medical.autism.GeneralResponse;
import com.medical.autism.parent.model.GetTrainersResponse;
import com.medical.autism.parent.model.PeriodsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ParentService {


    @GET("trainer")
    Observable<GetTrainersResponse> getTrainers(
            @Header("Authorization")String token
            );

    @POST("review")
    Observable<GeneralResponse> review(
            @Header("Authorization")String token,
            @Query("trainer_id") String trainer_id,
            @Query("parent_id") String parent_id,
            @Query("value") String value
    );

    @GET("appointment")
    Observable<PeriodsResponse> getPeriods(
            @Header("Authorization")String token,
            @Query("id") Integer trainerId,
            @Query("day") String day
    );

}
