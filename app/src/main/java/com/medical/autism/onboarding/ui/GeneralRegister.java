package com.medical.autism.onboarding.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.onboarding.OnboardingProcess;


public class GeneralRegister extends BaseFragment {

    EditText firstname;
    EditText lastname;
    EditText username;
    EditText email;
    EditText password;
    EditText mobileNumber;
    RadioButton trainer;
    RadioButton pateint;
    Button next;
    public GeneralRegister() { super(R.layout.fragment_general_register);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstname     = requireActivity().findViewById(R.id.generalRegisterFirstName);
        lastname     = requireActivity().findViewById(R.id.generalRegisterLastname);
        username     = requireActivity().findViewById(R.id.generalRegisterUsername);
        email        = requireActivity().findViewById(R.id.generalRegisterEmail);
        password     = requireActivity().findViewById(R.id.generalRegisterPassword);
        mobileNumber = requireActivity().findViewById(R.id.generalRegisterMobileNumber);
        trainer      = requireActivity().findViewById(R.id.generalRegisterTrainer);
        pateint      = requireActivity().findViewById(R.id.generalRegisterPatient);
        next         = requireActivity().findViewById(R.id.generalRegisterNext);
        next.setOnClickListener(v ->{
            if (trainer.isChecked()) {
                ((OnboardingProcess) requireActivity()).replaceFragment(new TrainerRegistration(
                        firstname.getText().toString(),
                        lastname.getText().toString(),
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        mobileNumber.getText().toString()
                ));
            }
            if (pateint.isChecked()) {
                ((OnboardingProcess) requireActivity()).replaceFragment(new PateintRegistration(
                        firstname.getText().toString(),
                        lastname.getText().toString(),
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        mobileNumber.getText().toString()
                ));
            }
        });
    }
}