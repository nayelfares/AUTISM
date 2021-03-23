package com.medical.autism.onboarding.ui;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.onboarding.vm.TrainerRegistrationViewModel;

public class TrainerRegistration extends BaseFragment implements TrainerRegistrationView {

    TrainerRegistrationViewModel trainerRegistrationViewModel;
    String username;
    String email;
    String password;
    String mobileNumber;

    public TrainerRegistration(String username,String email,String password,String mobileNumber) {
        super(R.layout.fragment_trainer_registration);
        this.username     = username;
        this.email        = email;
        this.password     = password;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trainerRegistrationViewModel = new TrainerRegistrationViewModel(this,requireContext());
    }
}