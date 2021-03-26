package com.medical.autism.parent.ui;

import com.medical.autism.parent.model.Period;

public interface AppointmentsView {
    void getPeriodsSuccess(Period[] data);

    void getPeriodsFailed(String message);
}
