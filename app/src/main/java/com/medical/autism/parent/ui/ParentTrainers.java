package com.medical.autism.parent.ui;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.parent.model.Trainer;
import com.medical.autism.parent.vm.ParentTrainersViewModel;
import com.medical.autism.parent.vm.TrainerAdapter;

public class ParentTrainers extends BaseFragment implements ParentTrainersView{
    ParentTrainersViewModel parentTrainersViewModel;
    RecyclerView trainersRecyclerView;
    public ParentTrainers() {
        super(R.layout.fragment_parent_trainers);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trainersRecyclerView    = requireActivity().findViewById(R.id.trainersRecyclerView);
        parentTrainersViewModel = new ParentTrainersViewModel(this,requireContext());
        loading();
        parentTrainersViewModel.getTrainers();
    }

    @Override
    public void getTrainersSuccess(Trainer[] trainers) {
        stopLoading();
        trainersRecyclerView.setAdapter(new TrainerAdapter(requireContext(),trainers));
    }

    @Override
    public void getTrainersFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}