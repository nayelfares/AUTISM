package com.medical.autism.parent.api;

import com.medical.autism.parent.model.GetTrainersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ParentService {


    @GET("trainer")
    Observable<GetTrainersResponse> getTrainers(
            @Header("Authorization")String token
            );

}
