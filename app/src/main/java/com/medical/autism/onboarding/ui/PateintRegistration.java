package com.medical.autism.onboarding.ui;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;

public class PateintRegistration extends BaseFragment {
    String username;
    String email;
    String password;
    String mobileNumber;

    public PateintRegistration(String username,String email,String password,String mobileNumber) {
        super(R.layout.fragment_pateint_registration);
        this.username     = username;
        this.email        = email;
        this.password     = password;
        this.mobileNumber = mobileNumber;
    }



}