package com.medical.autism.onboarding.vm;

import android.content.Context;

import com.medical.autism.onboarding.ui.LoginView;

public class LoginViewModel {
    Context context;
    LoginView loginView;

    public LoginViewModel(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }
}
