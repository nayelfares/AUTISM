package com.medical.autism.trainer.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.vm.TrainerAppointmentsViewModel;

public class TrainerAppointments extends BaseFragment implements TrainerAppointmentsView {
    TrainerAppointmentsViewModel trainerAppointmentsViewModel;
    RecyclerView trainerAppointmentsContent;
    EditText     chossedDay;
    Button       showAppointments;
    public TrainerAppointments() {
        super(R.layout.fragment_trainer_appointments);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trainerAppointmentsViewModel = new TrainerAppointmentsViewModel(requireContext(),this);
        trainerAppointmentsContent   =  requireActivity().findViewById(R.id.trainerAppointmentsContent);
        chossedDay                   =  requireActivity().findViewById(R.id.chossedDay);
        showAppointments             =  requireActivity().findViewById(R.id.showAppointments);
        chossedDay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN ){
                    Dialog dialog =
                            new Dialog(requireContext(), R.style.Theme_Design_BottomSheetDialog);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.pick_date);
                    dialog.findViewById(R.id.datePickerCancel).setOnClickListener(v->{
                        dialog.dismiss();
                    });
                    dialog.findViewById(R.id.datePickerOk).setOnClickListener(v->{
                        DatePicker datePicker= dialog.findViewById(R.id.datePicker);
                        String pirthdateString=datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth();
                        chossedDay.setText(pirthdateString);
                        dialog.dismiss();
                    });

                    Window window = dialog.getWindow();
                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    dialog.show();
                }
                return false;
            }
        });
        trainerAppointmentsViewModel.getAppointments();
    }
}