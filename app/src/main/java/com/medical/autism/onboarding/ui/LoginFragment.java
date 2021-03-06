package com.medical.autism.onboarding.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medical.autism.BaseFragment;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.R;
import com.medical.autism.onboarding.OnboardingProcess;
import com.medical.autism.onboarding.model.LoginData;
import com.medical.autism.onboarding.vm.LoginViewModel;
import com.medical.autism.trainer.TrainerActivity;


public class LoginFragment extends BaseFragment implements LoginView {
    LoginViewModel loginViewModel;
    public LoginFragment() {
        super(R.layout.fragment_login);
    }
    Button login ;
    EditText email;
    EditText password;
    TextView register;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new LoginViewModel(requireContext(),this);
        login = requireActivity().findViewById(R.id.login);
        email = requireActivity().findViewById(R.id.email);
        password = requireActivity().findViewById(R.id.password);
        register = requireActivity().findViewById(R.id.register);
        login.setOnClickListener(v -> {
            loading();
            loginViewModel.login(email.getText().toString(), password.getText().toString());
        });
        register.setOnClickListener(v->{
            ((OnboardingProcess) requireActivity()).replaceFragment(new GeneralRegister());
        });
    }

    @Override
    public void loginSuccess(LoginData data) {
        if (data.type.equals("parent")) {
            ParentActivity.id = data.id;
            ParentActivity.token = "Bearer " + data.token;
            ParentActivity.type = data.type;
            requireActivity().startActivity(new Intent(requireContext(), ParentActivity.class));
        }else{
            TrainerActivity.id = data.id;
            TrainerActivity.token = "Bearer " + data.token;
            TrainerActivity.type = data.type;
            requireActivity().startActivity(new Intent(requireContext(), TrainerActivity.class));
        }
        stopLoading();
        requireActivity().finish();
    }

    @Override
    public void loginFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}