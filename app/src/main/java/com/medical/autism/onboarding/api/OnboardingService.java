package com.medical.autism.onboarding.api;


import com.medical.autism.onboarding.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OnboardingService {
    @POST("user-login")
    Observable<Response> login(@Query("email") String email, @Query("password") String password);
}
