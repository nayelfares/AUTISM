package com.medical.autism.parent.ui;

import com.medical.autism.parent.model.Trainer;

public interface ParentTrainersView {
    void getTrainersSuccess(Trainer[] trainers);

    void getTrainersFailed(String message);
}
