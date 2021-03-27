package com.medical.autism.parent.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.medical.Util;
import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.parent.model.Parent;
import com.medical.autism.parent.model.ParentProfileData;
import com.medical.autism.parent.vm.AppointmentAdapter;
import com.medical.autism.parent.vm.ParentProfileViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class ParentProfile extends BaseFragment implements ParentProfileView{
    ParentProfileViewModel parentProfileViewModel;
    public ParentProfile() {
        super(R.layout.fragment_parent_profile);
    }

    CircleImageView parentProfilePhoto;
    EditText        parentProfileUsername;
    EditText        parentProfileFirstname;
    EditText        parentProfileLastname;
    EditText        parentProfileEmail;
    EditText        parentProfilePhone;
    Spinner         parentProfileMarraigeStatus;
    Spinner         parentProfileParentGender;
    EditText        parentBirthDate;
    EditText        parentDetails;
    EditText        parentChildName;
    EditText        parentChildAge;
    Spinner         parentProfileGender;
    EditText        parentChildNumber;
    EditText        parentChildMainProblem;
    RecyclerView    parentProfileAppointments;
    Button          parentProfileSubmit;
    TextView        notAvailableAppoitments;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parentProfileViewModel              = new ParentProfileViewModel(requireContext(),this);
        parentProfilePhoto                  = requireActivity().findViewById(R.id.parentProfilePhoto);
        parentProfileUsername               = requireActivity().findViewById(R.id.parentProfileUsername);
        parentProfileFirstname              = requireActivity().findViewById(R.id.parentProfileFirstname);
        parentProfileLastname               = requireActivity().findViewById(R.id.parentProfileLastname);
        parentProfileEmail                  = requireActivity().findViewById(R.id.parentProfileEmail);
        parentProfilePhone                  = requireActivity().findViewById(R.id.parentProfilePhone);
        parentProfileMarraigeStatus         = requireActivity().findViewById(R.id.parentProfileMarraigeStatus);
        parentProfileParentGender           = requireActivity().findViewById(R.id.parentProfileParentGender);
        parentBirthDate                     = requireActivity().findViewById(R.id.parentBirthDate);
        parentDetails                       = requireActivity().findViewById(R.id.parentDetails);
        parentChildName                     = requireActivity().findViewById(R.id.parentChildName);
        parentChildAge                      = requireActivity().findViewById(R.id.parentChildAge);
        parentProfileGender                 = requireActivity().findViewById(R.id.parentProfileGender);
        parentChildNumber                   = requireActivity().findViewById(R.id.parentChildNumber);
        parentChildMainProblem              = requireActivity().findViewById(R.id.parentChildMainProblem);
        notAvailableAppoitments             = requireActivity().findViewById(R.id.notAvailableAppoitments);
        parentProfileAppointments           = requireActivity().findViewById(R.id.parentProfileAppointments);
        parentProfileSubmit                 = requireActivity().findViewById(R.id.parentProfileSubmit);

        String[] items = new String[]{"Married","widow","Divorced"};
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,items);
        parentProfileMarraigeStatus.setAdapter(adapter);

        String[] genders = new String[]{"Male","Female"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,genders);
        parentProfileParentGender.setAdapter(genderAdapter);


        parentProfileGender.setAdapter(genderAdapter);

        loading();
        parentProfileViewModel.getProfile();
    }

    @Override
    public void getProfileSuccess(ParentProfileData data) {
        Parent parent = data.user;
        stopLoading();
        Glide.with(requireContext())
                .load(Util.toUrl(parent.photo))
                .into(parentProfilePhoto);
        parentProfileUsername.setText(parent.user_temp_name);
        parentProfileFirstname.setText(parent.first_name);
        parentProfileLastname.setText(parent.last_name);
        parentProfileEmail.setText(parent.email);
        parentProfilePhone.setText(parent.phone);
        if (parent.marriage_status!=null) {
            if (parent.marriage_status.equals("Married"))
                parentProfileMarraigeStatus.setSelection(0);
            if (parent.marriage_status.equals("widow"))
                parentProfileMarraigeStatus.setSelection(1);
            if (parent.marriage_status.equals("Divorced"))
                parentProfileMarraigeStatus.setSelection(2);
        }
        if (parent.parent_gender!=null) {
            if (parent.parent_gender.equals("Male"))
                parentProfileParentGender.setSelection(0);
            if (parent.parent_gender.equals("Female"))
                parentProfileParentGender.setSelection(1);
        }
        parentBirthDate.setText(parent.dob);
        parentDetails.setText(parent.details);
        parentChildName.setText(parent.child_name);
        parentChildAge.setText(parent.child_age);
        parentProfilePhone.setText(parent.phone);
        if (parent.gender!=null) {
            if (parent.gender.equals("Male"))
                parentProfileGender.setSelection(0);
            if (parent.gender.equals("Female"))
                parentProfileGender.setSelection(1);
        }
        parentChildNumber.setText(parent.child_number);
        parentChildMainProblem.setText(parent.child_main_problem);
        if (data.appointment.size()>0) {
            parentProfileAppointments.setAdapter(new AppointmentAdapter(this, data.appointment));
            notAvailableAppoitments.setVisibility(View.GONE);
            parentProfileAppointments.setVisibility(View.VISIBLE);
        }else{
            notAvailableAppoitments.setVisibility(View.VISIBLE);
            parentProfileAppointments.setVisibility(View.GONE);
        }
    }

    @Override
    public void getProfileFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}