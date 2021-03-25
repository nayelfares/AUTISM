package com.medical.autism.onboarding.ui;

import com.medical.autism.onboarding.model.LoginData;

public interface LoginView {
    void loginSuccess(LoginData data);

    void loginFailed(String message);
}
