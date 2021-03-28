package com.medical.autism.trainer.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.model.TrainerPatient;
import com.medical.autism.trainer.vm.EditPatientViewModel;

public class EditPatient extends BaseFragment implements EditPatientView{
    EditPatientViewModel editPatientViewModel;
    TrainerPatients trainerPatients;
    TrainerPatient patient;
    TextView editPatientFirstName;
    TextView editPatientLastName;
    TextView editPatientChildName;
    TextView editPatientchildAge;
    TextView editPatientParentJob;
    TextView editPatientMarriageStatus;
    TextView editPatientParentGender;
    TextView editPatientChildNumber;
    TextView editPatientTime;
    TextView editPatientDay;
    EditText editPatientMainProblem;
    Button   editPatientSave;

    public EditPatient(TrainerPatients trainerPatients,TrainerPatient patient) {
        super(R.layout.fragment_edit_patient);
        this.patient          = patient;
        this.trainerPatients  = trainerPatients;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editPatientViewModel       = new EditPatientViewModel(requireContext(),this);
        editPatientFirstName       = requireActivity().findViewById(R.id.editPatientFirstName);
        editPatientLastName        = requireActivity().findViewById(R.id.editPatientLastName);
        editPatientChildName       = requireActivity().findViewById(R.id.editPatientChildName);
        editPatientchildAge        = requireActivity().findViewById(R.id.editPatientchildAge);
        editPatientParentJob       = requireActivity().findViewById(R.id.editPatientParentJob);
        editPatientMarriageStatus  = requireActivity().findViewById(R.id.editPatientMarriageStatus);
        editPatientParentGender    = requireActivity().findViewById(R.id.editPatientParentGender);
        editPatientChildNumber     = requireActivity().findViewById(R.id.editPatientChildNumber);
        editPatientTime            = requireActivity().findViewById(R.id.editPatientTime);
        editPatientDay             = requireActivity().findViewById(R.id.editPatientDay);
        editPatientMainProblem     = requireActivity().findViewById(R.id.editPatientMainProblem);
        editPatientSave            = requireActivity().findViewById(R.id.editPatientSave);

        editPatientFirstName.setText(patient.first_name);
        editPatientLastName.setText(patient.last_name);
        editPatientChildName.setText(patient.child_name);
        editPatientchildAge.setText(patient.child_age);
        editPatientParentJob.setText(patient.parent_job);
        editPatientMarriageStatus.setText(patient.marriage_status);
        editPatientParentGender.setText(patient.parent_gender);
        editPatientChildNumber.setText(patient.child_number);
        editPatientTime.setText(patient.time);
        editPatientDay.setText(patient.day);
        editPatientMainProblem.setText(patient.child_main_problem);

        editPatientSave.setOnClickListener(v->{
            loading();
            editPatientViewModel.updateMainProblem(patient.user_id,editPatientMainProblem.getText().toString());
        });

    }

    @Override
    public void updateMainProblemSuccess(String message) {
        stopLoading();
        showMessage(message);
        trainerPatients.reload();
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void updateMainProblemFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}