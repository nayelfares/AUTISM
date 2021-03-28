package com.medical.autism.trainer.ui;

import com.medical.autism.trainer.model.TrainerPatient;

import java.util.ArrayList;

public interface TrainerPatientsView {
    void getPatientSuccess(ArrayList<TrainerPatient> data);

    void getPatientFailed(String message);
}
