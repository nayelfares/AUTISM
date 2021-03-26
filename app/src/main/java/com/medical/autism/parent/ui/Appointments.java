package com.medical.autism.parent.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.parent.model.Period;
import com.medical.autism.parent.vm.AppointmentsViewModel;
import com.medical.autism.parent.vm.PeriodAdapter;

public class Appointments extends BaseFragment implements AppointmentsView {
    AppointmentsViewModel appointmentsViewModel;
    TextView noItems;
    Integer trainerID;
    String day;
    RecyclerView recyclerView;
    public Appointments(Integer trainerID,String day) {
        super(R.layout.fragment_appointments);
        this.trainerID  = trainerID;
        this.day        = day;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appointmentsViewModel = new AppointmentsViewModel(requireContext(),this);
        noItems               = requireActivity().findViewById(R.id.noItems);
        recyclerView          = requireActivity().findViewById(R.id.periodsRecyclerView);
        loading();
        appointmentsViewModel.getPeriods(trainerID,day);
    }

    @Override
    public void getPeriodsSuccess(Period[] data) {
        for (Period period : data) {
            period.time = period.time.substring(0, 2);
            period.end_time = String.valueOf(Integer.parseInt(period.time) + 1);
            if (period.end_time.equals("25"))
                period.end_time="01";
            if (period.end_time.length()==1) {
                period.end_time="0"+period.end_time;
            }
            period.selected = false;
        }
        recyclerView.setAdapter(new PeriodAdapter(requireContext(), data));
        stopLoading();
    }

    @Override
    public void getPeriodsFailed(String message) {
        stopLoading();
        showMessage(message);
        noItems.setVisibility(View.VISIBLE);
    }
}