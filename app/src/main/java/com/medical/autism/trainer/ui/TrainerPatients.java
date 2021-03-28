package com.medical.autism.trainer.ui;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.model.TrainerPatient;
import com.medical.autism.trainer.vm.PatientAdapter;
import com.medical.autism.trainer.vm.TrainerPatientsViewModel;

import java.util.ArrayList;

public class TrainerPatients extends BaseFragment implements TrainerPatientsView{
    TrainerPatientsViewModel trainerPatientsViewModel;
    RecyclerView             patientsRecyclerView;
    public TrainerPatients() {
        super(R.layout.fragment_trainer_patients);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trainerPatientsViewModel = new TrainerPatientsViewModel(requireContext(),this);
        patientsRecyclerView = requireActivity().findViewById(R.id.patientsRecyclerView);
        loading();
        trainerPatientsViewModel.getPatient();
    }

    @Override
    public void getPatientSuccess(ArrayList<TrainerPatient> data) {
        patientsRecyclerView.setAdapter(new PatientAdapter(this,data));
        stopLoading();
        if (data.size()==0){
            showMessage("No patients found");
        }
    }

    @Override
    public void getPatientFailed(String message) {
        showMessage(message);
        stopLoading();

    }

    public void reload() {
        loading();
        trainerPatientsViewModel.getPatient();
    }
}