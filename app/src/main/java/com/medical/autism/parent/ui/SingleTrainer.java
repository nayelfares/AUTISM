package com.medical.autism.parent.ui;

import com.medical.autism.BaseFragment;
import com.medical.autism.R;
import com.medical.autism.parent.model.Trainer;

public class SingleTrainer extends BaseFragment {
    Trainer trainer;

    public SingleTrainer(Trainer trainer) {
        super(R.layout.fragment_single_trainer);
        this.trainer  =  trainer;
    }

}