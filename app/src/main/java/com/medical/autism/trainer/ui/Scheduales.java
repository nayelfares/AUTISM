package com.medical.autism.trainer.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.model.Schedule;
import com.medical.autism.trainer.vm.SchedualesViewModel;
import com.medical.autism.trainer.vm.ScheduleAdapter;

import java.util.ArrayList;


public class Scheduales extends BaseFragment implements SchedualesView {
    SchedualesViewModel schedualesViewModel;
    RecyclerView        scheduleContent;
    ImageButton         addSchedule;
    public Scheduales() {
        super(R.layout.fragment_scheduales);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        schedualesViewModel  = new SchedualesViewModel(requireContext(),this);
        scheduleContent      = requireActivity().findViewById(R.id.scheduleContent);
        addSchedule          = requireActivity().findViewById(R.id.addSchedule);
        loading();
        schedualesViewModel.getSchedules();
        addSchedule.setOnClickListener(v->{
            ((TrainerActivity) requireActivity()).replaceFragment(new AddSchedule(this));
        });
    }

    @Override
    public void getSchedulesSuccess(ArrayList<Schedule> data) {
        stopLoading();
        scheduleContent.setAdapter(new ScheduleAdapter(requireContext(),data));
    }

    @Override
    public void getSchedulesFailed(String message) {
        stopLoading();
        showMessage(message);
    }

    public void reload(){
        loading();
        schedualesViewModel.getSchedules();
    }
}