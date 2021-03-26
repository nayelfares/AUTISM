package com.medical.autism.parent.vm;

import android.content.Context;

import com.medical.autism.parent.ui.SingleTrainerView;

public class SingleTrainerViewModel {
    Context context;
    SingleTrainerView singleTrainerView;

    public SingleTrainerViewModel(Context context, SingleTrainerView singleTrainerView) {
        this.context = context;
        this.singleTrainerView = singleTrainerView;
    }
}
