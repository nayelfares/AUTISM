package com.medical.autism.onboarding.vm;

import android.content.Context;

import com.medical.autism.onboarding.ui.TrainerRegistrationView;

public class TrainerRegistrationViewModel {
    public TrainerRegistrationView trainerRegistrationView;
    public Context context;

    public TrainerRegistrationViewModel(TrainerRegistrationView trainerRegistrationView, Context context) {
        this.trainerRegistrationView = trainerRegistrationView;
        this.context = context;
    }

}
