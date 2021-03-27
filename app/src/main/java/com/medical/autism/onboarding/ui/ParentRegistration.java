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

public class ParentRegistration extends BaseFragment implements RegistrationView {
    RegistrationViewModel registrationViewModel;
    String firstname;
    String lastname;
    String username;
    String email;
    String password;
    String mobileNumber;
    String type = "parent";


    EditText child_name;
    EditText child_age;
    EditText parent_job;
    Spinner marriage_status;
    Spinner parent_gender;
    EditText child_number;
    EditText child_main_problem;
    CheckBox conditions;
    Button register;

    public ParentRegistration(String firstname, String lastname, String username, String email, String password, String mobileNumber) {
        super(R.layout.fragment_parent_registration);
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
        child_name          = requireActivity().findViewById(R.id.parentRegistrationChildName);
        child_age            = requireActivity().findViewById(R.id.parentRegistrationChildAge);
        parent_job          = requireActivity().findViewById(R.id.parentRegistrationParentJob);
        marriage_status          = requireActivity().findViewById(R.id.parentRegistrationMarriageStatus);
        parent_gender            = requireActivity().findViewById(R.id.parentRegistrationParentGender);
        child_number            = requireActivity().findViewById(R.id.parenttRegistrationChildNumber);
        child_main_problem            = requireActivity().findViewById(R.id.parentRegistrationMainProblem);
        conditions          = requireActivity().findViewById(R.id.parentRegisterConditions);
        register            = requireActivity().findViewById(R.id.parentRegister);


        String[] items = new String[]{"Married","widow","Divorced"};
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,items);
        marriage_status.setAdapter(adapter);

        String[] genders = new String[]{"Male","Female"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,genders);
        parent_gender.setAdapter(genderAdapter);

        register.setOnClickListener(v->{
            if (conditions.isChecked()){
                loading();
                registrationViewModel.register(
                        firstname,lastname,username,email,password,mobileNumber,type,
                        "","","","","","",
                        child_name.getText().toString(),
                        child_age.getText().toString(),
                        parent_job.getText().toString(),
                        marriage_status.getSelectedItem().toString(),
                        parent_gender.getSelectedItem().toString(),
                        child_number.getText().toString(),
                        child_main_problem.getText().toString()
                );
            }else
                showMessage(requireContext().getResources().getString(R.string.approve_conditions_first));
        });



    }


    @Override
    public void registrationSuccess(String message) {
        stopLoading();
        showMessage(message);
        LoginFragment fragment =(LoginFragment) requireActivity().getSupportFragmentManager().getFragments().get(0);
        fragment.email.setText(email);
        fragment.password.setText(password);
        requireActivity().getSupportFragmentManager().popBackStack();
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void registrationFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}