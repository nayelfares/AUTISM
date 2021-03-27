package com.medical.autism;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medical.autism.onboarding.api.OnboardingService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    public static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://crm.towarddevelopment.org/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
