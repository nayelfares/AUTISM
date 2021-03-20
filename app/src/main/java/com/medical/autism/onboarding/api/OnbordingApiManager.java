package com.medical.autism.onboarding.api;

import com.medical.autism.ApiManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnbordingApiManager {
    public static OnboardingService onboardingService = ApiManager.retrofit.create(OnboardingService.class);
}


