package com.medical.autism.trainer.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.trainer.vm.AddScheduleViewModel;

public class AddSchedule extends BaseFragment  implements AddScheduleView{
    AddScheduleViewModel addScheduleViewModel;
    EditText             addScheduleDay;
    EditText             addScheduleFrom;
    EditText             addScheduleTo;
    AppCompatButton      addScheduleSubmit;

    public AddSchedule() {
        super(R.layout.fragment_add_schedule);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addScheduleViewModel   = new AddScheduleViewModel(requireContext(),this);
        addScheduleDay         = requireActivity().findViewById(R.id.addScheduleDay);
        addScheduleFrom        = requireActivity().findViewById(R.id.addScheduleFrom);
        addScheduleTo          = requireActivity().findViewById(R.id.addScheduleTo);
        addScheduleSubmit      = requireActivity().findViewById(R.id.addScheduleSubmit);
    }
}