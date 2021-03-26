package com.medical.autism.parent.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.medical.Util;
import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.parent.model.Trainer;
import com.medical.autism.parent.vm.SingleTrainerViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingleTrainer extends BaseFragment implements SingleTrainerView{
    SingleTrainerViewModel singleTrainerViewModel;
    Trainer         trainer;
    CircleImageView trainerPhoto;
    TextView        trainerName;
    TextView        certificateNumber;
    TextView        trainerPhone;
    TextView        trainerEmail;
    TextView        trainerdob;
    TextView        trainerClinicAddress;
    TextView        trainerGender;
    TextView        trainerSpecialization;
    TextView        trainerAvaliableTime;
    TextView        trainerCollage;
    TextView        trainerExperienceYears;
    RatingBar       rating;
    Button          submitRating;
    TextView        trainerLanguage;
    TextView        trainerPreviousClinics;
    TextView        trainerDetails;

    public SingleTrainer(Trainer trainer) {
        super(R.layout.fragment_single_trainer);
        this.trainer               =  trainer;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        singleTrainerViewModel     = new SingleTrainerViewModel(requireContext(),this);
        trainerPhoto               = requireActivity().findViewById(R.id.trainerPhoto);
        trainerName                = requireActivity().findViewById(R.id.trainerName);
        certificateNumber          = requireActivity().findViewById(R.id.certificateNumber);
        trainerPhone               = requireActivity().findViewById(R.id.trainerPhone);
        trainerEmail               = requireActivity().findViewById(R.id.trainerEmail);
        trainerdob                 = requireActivity().findViewById(R.id.trainerdob);
        trainerClinicAddress             = requireActivity().findViewById(R.id.trainerClinicAddress);
        trainerGender              = requireActivity().findViewById(R.id.trainerGender);
        trainerSpecialization      = requireActivity().findViewById(R.id.trainerSpecialization);
        trainerAvaliableTime       = requireActivity().findViewById(R.id.trainerAvaliableTime);
        trainerCollage             = requireActivity().findViewById(R.id.trainerCollage);
        trainerExperienceYears     = requireActivity().findViewById(R.id.trainerExperienceYears);
        trainerLanguage            = requireActivity().findViewById(R.id.trainerLanguage);
        trainerPreviousClinics     = requireActivity().findViewById(R.id.trainerPreviousClinics);
        trainerDetails             = requireActivity().findViewById(R.id.trainerDetails);
        rating                     = requireActivity().findViewById(R.id.trainerRating);
        submitRating               = requireActivity().findViewById(R.id.submitRating);
        appendValues();
    }

    private void appendValues() {
        Glide.with(requireContext())
                .load(Util.toUrl(trainer.photo))
                .into(trainerPhoto);
        trainerName           .setText(trainer.first_name+" "+trainer.last_name);
        certificateNumber     .setText(trainer.certificate_number);
        trainerPhone          .setText(trainer.phone);
        trainerEmail          .setText(trainer.email);
        trainerdob            .setText(trainer.dob);
        trainerClinicAddress  .setText(trainer.clinic_address);
        trainerGender         .setText(trainer.gender);
        trainerSpecialization .setText(trainer.speciality);
        trainerAvaliableTime  .setText(trainer.avaliable_time);
        trainerCollage        .setText(trainer.collage);
        trainerExperienceYears.setText(trainer.experience_years);
        trainerLanguage       .setText(trainer.language);
        trainerPreviousClinics.setText(trainer.previous_clinics);
        trainerDetails        .setText(trainer.details);
        rating                .setRating(trainer.review);
        rating.setOnRatingBarChangeListener((ratingBar, v, b) -> submitRating.setVisibility(View.VISIBLE));
        submitRating.setOnClickListener(v->{
            loading();
            singleTrainerViewModel.review(trainer.id,rating.getRating());
        });
    }

    @Override
    public void reviewSuccess(String message) {
        stopLoading();
        submitRating.setVisibility(View.GONE);
        rating.setIsIndicator(true);
        showMessage(message);
    }

    @Override
    public void reviewFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}