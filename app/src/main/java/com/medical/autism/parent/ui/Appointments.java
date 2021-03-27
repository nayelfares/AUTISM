package com.medical.autism.parent.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    LinearLayout appointmentContainer;
    Button submitAppointment;
    TextView appointmentDateTime;
    Integer trainerID;
    String day;
    Integer schedule_id ;
    String  time ;
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
        appointmentContainer  = requireActivity().findViewById(R.id.appointmentContainer);
        submitAppointment     = requireActivity().findViewById(R.id.submitAppointment);
        appointmentDateTime   = requireActivity().findViewById(R.id.appointmentDateTime);
        recyclerView          = requireActivity().findViewById(R.id.periodsRecyclerView);
        loading();
        appointmentsViewModel.getPeriods(trainerID,day);
        submitAppointment.setOnClickListener(v->{
            loading();
            appointmentsViewModel.getAppointment(schedule_id,trainerID,time);
        });
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
        recyclerView.setAdapter(new PeriodAdapter(this, data));
        stopLoading();
    }

    @Override
    public void getPeriodsFailed(String message) {
        stopLoading();
        showMessage(message);
        noItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void getAppointmentSuccess(String message) {
        stopLoading();
        showMessage(message);
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void getAppointmentFailed(String message) {
        stopLoading();
        showMessage(message);
    }

    public void selectTime(String time,Integer schedule_id){
        submitAppointment.setVisibility(View.VISIBLE);
        appointmentContainer.setVisibility(View.VISIBLE);
        appointmentDateTime.setText(day +" , "+time +"-"+(Integer.parseInt(time) + 1));
        this.schedule_id = schedule_id;
        if (time.length()==2)
            this.time = time+":00:00";
        else
            this.time = "0"+time+":00:00";
    }
}