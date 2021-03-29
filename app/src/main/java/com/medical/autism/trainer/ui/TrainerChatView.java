package com.medical.autism.trainer.ui;

import com.medical.autism.trainer.model.TrainerPatient;

import java.util.ArrayList;

public interface TrainerChatView {
    void getParentsSuccess(ArrayList<TrainerPatient> data);

    void getParentsFailed(String message);
}
