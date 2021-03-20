package com.medical.autism.onboarding.ui;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.medical.autism.R;
import com.medical.autism.onboarding.vm.LoginViewModel;


public class LoginFragment extends Fragment implements LoginView {
    LoginViewModel loginViewModel;
    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new LoginViewModel(requireContext(),this);
    }
}