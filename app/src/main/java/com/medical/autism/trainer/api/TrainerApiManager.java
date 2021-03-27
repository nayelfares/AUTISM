package com.medical.autism.trainer.api;

import com.medical.autism.ApiManager;
import com.medical.autism.onboarding.api.OnboardingService;

public class TrainerApiManager {
    public static TrainerService trainerService = ApiManager.retrofit.create(TrainerService.class);
}
