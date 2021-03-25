package com.medical.autism.onboarding.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.onboarding.vm.RegistrationViewModel;

import retrofit2.http.Query;

public class PateintRegistration extends BaseFragment implements RegistrationView {
    RegistrationViewModel registrationViewModel;
    String firstname;
    String lastname;
    String username;
    String email;
    String password;
    String mobileNumber;
    String type = "pateint";


    EditText child_name;
    EditText child_age;
    EditText parent_job;
    Spinner marriage_status;
    EditText parent_gender;
    EditText child_number;
    EditText child_main_problem;
    CheckBox conditions;
    Button register;

    public PateintRegistration(String firstname,String lastname,String username,String email,String password,String mobileNumber) {
        super(R.layout.fragment_pateint_registration);
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
        child_name          = requireActivity().findViewById(R.id.pateintRegistrationChildName);
        child_age            = requireActivity().findViewById(R.id.pateintRegistrationChildAge);
        parent_job          = requireActivity().findViewById(R.id.pateintRegistrationParentJob);
        marriage_status          = requireActivity().findViewById(R.id.pateintRegistrationMarriageStatus);
        parent_gender            = requireActivity().findViewById(R.id.pateintRegistrationPateintGender);
        child_number            = requireActivity().findViewById(R.id.pateintRegistrationChildNumber);
        child_main_problem            = requireActivity().findViewById(R.id.pateintRegistrationMainProblem);
        conditions          = requireActivity().findViewById(R.id.trainerRegisterConditions);
        register            = requireActivity().findViewById(R.id.trainerRegister);


        String[] items = new String[]{"Married","widow","Divorced"};
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,items);
        marriage_status.setAdapter(adapter);

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
                        child_name.getText(),"","","","","",""
                );
            }else
                showMessage(requireContext().getResources().getString(R.string.approve_conditions_first));
        });
    }


    @Override
    public void registrationSuccess(String message) {

    }

    @Override
    public void registrationFailed(String message) {

    }
}