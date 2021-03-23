package com.medical.autism.onboarding.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.onboarding.vm.LoginViewModel;


public class LoginFragment extends BaseFragment implements LoginView {
    LoginViewModel loginViewModel;
    public LoginFragment() {
        super(R.layout.fragment_login);
    }
    Button login ;
    EditText email;
    EditText password;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new LoginViewModel(requireContext(),this);
        login = requireActivity().findViewById(R.id.login);
        email = requireActivity().findViewById(R.id.email);
        password = requireActivity().findViewById(R.id.password);
        login.setOnClickListener(v -> {
            loading();
            loginViewModel.login(email.getText().toString(), password.getText().toString());
        });
    }

    @Override
    public void loginSuccess(String message) {
        stopLoading();
        showMessage(message);
    }

    @Override
    public void loginFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}