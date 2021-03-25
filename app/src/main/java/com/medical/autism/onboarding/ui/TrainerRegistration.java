package com.medical.autism.onboarding.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.onboarding.vm.RegistrationViewModel;

public class TrainerRegistration extends BaseFragment implements RegistrationView {

    RegistrationViewModel registrationViewModel;
    String firstname;
    String lastname;
    String username;
    String email;
    String password;
    String mobileNumber;
    String type = "parent";

    EditText speciality;
    EditText collage;
    EditText previous_clincs;
    EditText clinic_address;
    EditText experience_years;
    EditText certificate_number;
    CheckBox conditions;
    Button register;

    public TrainerRegistration(String firstname,String lastname,String username,String email,String password,String mobileNumber) {
        super(R.layout.fragment_trainer_registration);
        this.firstname    = firstname;
        this.lastname     = lastname;
        this.username     = username;
        this.email        = email;
        this.password     = password;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registrationViewModel = new RegistrationViewModel(this,requireContext());

        speciality          = requireActivity().findViewById(R.id.trainerRegisterSpeciality);
        collage             = requireActivity().findViewById(R.id.trainerRegisterCollage);
        previous_clincs     = requireActivity().findViewById(R.id.trainerRegisterPreviousClinics);
        clinic_address      = requireActivity().findViewById(R.id.trainerRegisterClinicAddress);
        experience_years    = requireActivity().findViewById(R.id.trainerRegisterExperiensYears);
        certificate_number  = requireActivity().findViewById(R.id.trainerRegisterCertificateNumber);
        conditions          = requireActivity().findViewById(R.id.trainerRegisterConditions);
        register            = requireActivity().findViewById(R.id.trainerRegister);

        register.setOnClickListener(v->{
            if (conditions.isChecked()){
                loading();
                registrationViewModel.register(
                        firstname,lastname,username,email,password,mobileNumber,type,
                        speciality.getText().toString(),
                        collage.getText().toString(),
                        previous_clincs.getText().toString(),
                        clinic_address.getText().toString(),
                        experience_years.getText().toString(),
                        certificate_number.getText().toString(),
                        "","","","","","",""
                );
            }else
                showMessage(requireContext().getResources().getString(R.string.approve_conditions_first));
        });
    }

    @Override
    public void registrationSuccess(String message) {
        stopLoading();
        showMessage(message);
    }

    @Override
    public void registrationFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}