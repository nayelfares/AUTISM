package com.medical.autism.trainer.ui;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.model.TrainerPatient;
import com.medical.autism.trainer.vm.ParentChatAdapter;
import com.medical.autism.trainer.vm.TrainerChatViewModel;

import java.util.ArrayList;

public class TrainerChat extends BaseFragment implements TrainerChatView {
    RecyclerView parentsRecyclerView;
    TrainerChatViewModel trainerChatViewModel;
    public TrainerChat() {
        super(R.layout.fragment_trainer_chat);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parentsRecyclerView  = requireActivity().findViewById(R.id.parentsRecyclerView);
        trainerChatViewModel =new TrainerChatViewModel(requireContext(),this);
        loading();
        trainerChatViewModel.getParents();
    }

    @Override
    public void getParentsSuccess(ArrayList<TrainerPatient> data) {
        parentsRecyclerView.setAdapter(new ParentChatAdapter(this,data));
        stopLoading();
    }

    @Override
    public void getParentsFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}