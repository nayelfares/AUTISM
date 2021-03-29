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
import com.medical.autism.parent.vm.TrainerChatAdapter;

public class ParentChat extends BaseFragment implements ParentTrainersView{
    RecyclerView trainersChatRecyclerView;
    ParentTrainersViewModel parentTrainersViewModel;
    public ParentChat() {
        super(R.layout.fragment_parent_chat);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trainersChatRecyclerView   = requireActivity().findViewById(R.id.trainersChatRecyclerView);
        parentTrainersViewModel    = new ParentTrainersViewModel(this,requireContext());
        loading();
        parentTrainersViewModel.getTrainers();
    }

    @Override
    public void getTrainersSuccess(Trainer[] trainers) {
        stopLoading();
        trainersChatRecyclerView.setAdapter(new TrainerChatAdapter(this,trainers));
    }

    @Override
    public void getTrainersFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}