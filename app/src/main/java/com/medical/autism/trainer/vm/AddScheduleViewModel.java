package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.trainer.ui.AddScheduleView;

public class AddScheduleViewModel {
    Context context;
    AddScheduleView addScheduleView;

    public AddScheduleViewModel(Context context, AddScheduleView addScheduleView) {
        this.context         = context;
        this.addScheduleView = addScheduleView;
    }
}
