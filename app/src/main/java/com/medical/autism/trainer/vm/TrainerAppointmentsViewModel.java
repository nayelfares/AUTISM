package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.api.TrainerApiManager;
import com.medical.autism.trainer.model.ProfileResponse;
import com.medical.autism.trainer.model.TrainerAppointmentResponse;
import com.medical.autism.trainer.ui.TrainerAppointmentsView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrainerAppointmentsViewModel {
    Context context;
    TrainerAppointmentsView trainerAppointmentsView;

    public TrainerAppointmentsViewModel(Context context, TrainerAppointmentsView trainerAppointmentsView) {
        this.context = context;
        this.trainerAppointmentsView = trainerAppointmentsView;
    }

    public void getAppointments(String day) {
        Observable<TrainerAppointmentResponse> loginObservable =
                TrainerApiManager.trainerService.getAppointments(TrainerActivity.token,TrainerActivity.id,day);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrainerAppointmentResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TrainerAppointmentResponse response) {
                        if (response.success)
                            trainerAppointmentsView.getAppointmentsSuccess(response.data);
                        else
                            trainerAppointmentsView.getAppointmentsFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        trainerAppointmentsView.getAppointmentsFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
