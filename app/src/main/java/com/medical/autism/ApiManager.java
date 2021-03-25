package com.medical.autism;

import com.medical.autism.onboarding.api.OnboardingService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    public static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://crm.towarddevelopment.org/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
