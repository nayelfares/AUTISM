package com.medical.autism.trainer.ui;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.medical.Util;
import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.model.Trainer;
import com.medical.autism.trainer.model.TrainerProfileData;
import com.medical.autism.trainer.vm.TrainerProfileViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrainerProfile extends BaseFragment implements TrainerProfileView {
    TrainerProfileViewModel trainerProfileViewModel;
    public TrainerProfile() {
        super(R.layout.fragment_trainer_profile);
    }

    CircleImageView trainerProfilePhoto;
    EditText        trainerProfileUsername;
    EditText        trainerProfileFirstname;
    EditText        trainerProfileLastname;
    EditText        trainerProfileEmail;
    EditText        trainerProfilePhone;
    EditText        trainerProfileSpeciality;
    EditText        trainerProfileCollage;
    EditText        trainerProfilePreviousClinics;
    EditText        trainerProfileClinicAddress;
    EditText        trainerProfileExperiensYears;
    EditText        trainerProfileCertificateNumber;
    Button          trainerProfileSubmit;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trainerProfileViewModel              = new TrainerProfileViewModel(requireContext(),this);
        trainerProfilePhoto                  = requireActivity().findViewById(R.id.trainerProfilePhoto);
        trainerProfileUsername               = requireActivity().findViewById(R.id.trainerProfileUsername);
        trainerProfileFirstname              = requireActivity().findViewById(R.id.trainerProfileFirstname);
        trainerProfileLastname               = requireActivity().findViewById(R.id.trainerProfileLastname);
        trainerProfileEmail                  = requireActivity().findViewById(R.id.trainerProfileEmail);
        trainerProfilePhone                  = requireActivity().findViewById(R.id.trainerProfilePhone);
        trainerProfileSpeciality             = requireActivity().findViewById(R.id.trainerProfileSpeciality);
        trainerProfileCollage                = requireActivity().findViewById(R.id.trainerProfileCollage);
        trainerProfilePreviousClinics        = requireActivity().findViewById(R.id.trainerProfilePreviousClinics);
        trainerProfileClinicAddress          = requireActivity().findViewById(R.id.trainerProfileClinicAddress);
        trainerProfileExperiensYears         = requireActivity().findViewById(R.id.trainerProfileExperiensYears);
        trainerProfileCertificateNumber      = requireActivity().findViewById(R.id.trainerProfileCertificateNumber);
        trainerProfileSubmit                 = requireActivity().findViewById(R.id.trainerProfileSubmit);

        loading();
        trainerProfileViewModel.getProfile();

        trainerProfilePhoto.setOnClickListener(v->{
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(requireActivity());
        });

    }

    @Override
    public void getProfileSuccess(TrainerProfileData data) {
        Trainer parent = data.user;
        stopLoading();
        Glide.with(requireContext())
                .load(Util.toUrl(parent.photo))
                .into(trainerProfilePhoto);
        trainerProfileUsername.setText(parent.user_name);
        trainerProfileFirstname.setText(parent.first_name);
        trainerProfileLastname.setText(parent.last_name);
        trainerProfileEmail.setText(parent.email);
        trainerProfilePhone.setText(parent.phone);

        trainerProfileSpeciality.setText(parent.speciality);
        trainerProfileCollage.setText(parent.collage);
        trainerProfilePreviousClinics.setText(parent.previous_clinics);
        trainerProfileClinicAddress.setText(parent.clinic_address);
        trainerProfileExperiensYears.setText(parent.experience_years);

        trainerProfileCertificateNumber.setText(parent.certificate_number);


        trainerProfileSubmit.setOnClickListener(v->{
            loading();
            trainerProfileViewModel.updateProfile(
                    trainerProfileFirstname.getText().toString(),
                    trainerProfileLastname.getText().toString(),
                    trainerProfileUsername.getText().toString(),
                    trainerProfileEmail.getText().toString(),"",
                    trainerProfilePhone.getText().toString(),
                    "trainer",
                    trainerProfileSpeciality.getText().toString(),
                    trainerProfileCollage.getText().toString(),
                    trainerProfilePreviousClinics.getText().toString(),
                    trainerProfileClinicAddress.getText().toString(),
                    trainerProfileExperiensYears.getText().toString(),
                    trainerProfileCertificateNumber.getText().toString(),
                    "", "", "", "", "", "", ""
            );
        });
    }

    @Override
    public void getProfileFailed(String message) {
        stopLoading();
        showMessage(message);
    }

    @Override
    public void updatePhotoSuccess(String message, Uri uri) {
        Glide.with(requireContext())
                .load(uri)
                .into(trainerProfilePhoto);
        stopLoading();
        showMessage(message);
    }

    @Override
    public void updatePhotoFailed(String message) {
        stopLoading();
        showMessage(message);
    }

    @Override
    public void updateProfileSuccess(String message) {
        stopLoading();
        showMessage(message);
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void updateProfileFailed(String message) {
        stopLoading();
        showMessage(message);
        Log.e("Error",message);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Uri resultUri = result.getUri();
                loading();
                trainerProfileViewModel.updatePhoto(resultUri);
            }
        }
    }
}