package com.medical.autism.trainer.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatButton;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.vm.AddScheduleViewModel;

public class AddSchedule extends BaseFragment  implements AddScheduleView{
    AddScheduleViewModel addScheduleViewModel;
    Scheduales           scheduales;
    EditText             addScheduleDay;
    EditText             addScheduleFrom;
    EditText             addScheduleTo;
    AppCompatButton      addScheduleSubmit;

    public AddSchedule(Scheduales scheduales) {
        super(R.layout.fragment_add_schedule);
        this.scheduales  = scheduales;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addScheduleViewModel   = new AddScheduleViewModel(requireContext(),this);
        addScheduleDay         = requireActivity().findViewById(R.id.addScheduleDay);
        addScheduleFrom        = requireActivity().findViewById(R.id.addScheduleFrom);
        addScheduleTo          = requireActivity().findViewById(R.id.addScheduleTo);
        addScheduleSubmit      = requireActivity().findViewById(R.id.addScheduleSubmit);

        touchEvents();
    }


    @SuppressLint("ClickableViewAccessibility")
    void touchEvents(){
        addScheduleDay.setOnTouchListener(new View.OnTouchListener() {
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
                        addScheduleDay.setText(pirthdateString);
                        dialog.dismiss();
                    });

                    Window window = dialog.getWindow();
                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    dialog.show();
                }
                return false;
            }
        });
        addScheduleFrom.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN ){
                    Dialog dialog =
                            new Dialog(requireContext(), R.style.Theme_Design_BottomSheetDialog);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.pick_time);
                    dialog.findViewById(R.id.timePickerCancel).setOnClickListener(v->{
                        dialog.dismiss();
                    });
                    TimePicker timePicker= dialog.findViewById(R.id.timePicker);
                    dialog.findViewById(R.id.timePickerSubmit).setOnClickListener(v->{
                        int startTime=timePicker.getHour();
                        addScheduleFrom.setText(startTime+":00");
                        dialog.dismiss();
                    });
                    timePicker.setIs24HourView(true);
                    Window window = dialog.getWindow();
                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    dialog.show();
                }
                return false;
            }
        });
        addScheduleTo.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN ){
                    Dialog dialog =
                            new Dialog(requireContext(), R.style.Theme_Design_BottomSheetDialog);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.pick_time);
                    dialog.findViewById(R.id.timePickerCancel).setOnClickListener(v->{
                        dialog.dismiss();
                    });
                    TimePicker timePicker= dialog.findViewById(R.id.timePicker);
                    dialog.findViewById(R.id.timePickerSubmit).setOnClickListener(v->{

                        int endTime=timePicker.getHour();
                        addScheduleTo.setText(endTime+":00");
                        dialog.dismiss();
                    });
                    timePicker.setIs24HourView(true);
                    Window window = dialog.getWindow();
                    window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                    dialog.show();
                }
                return false;
            }
        });

        addScheduleSubmit.setOnClickListener(v->{
            loading();
           addScheduleViewModel.addSchedule(
                   addScheduleDay.getText().toString(),
                   addScheduleFrom.getText().toString(),
                   addScheduleTo.getText().toString()
           );
        });

    }

    @Override
    public void addScheduleSuccess(String message) {
        stopLoading();
        showMessage(message);
        scheduales.reload();
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void addScheduleFailed(String message) {
        stopLoading();
        showMessage(message);
    }
}