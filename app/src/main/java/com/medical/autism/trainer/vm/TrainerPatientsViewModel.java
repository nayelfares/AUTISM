package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.api.TrainerApiManager;
import com.medical.autism.trainer.model.TrainerPatientResponse;
import com.medical.autism.trainer.ui.TrainerPatientsView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrainerPatientsViewModel {
    Context             context;
    TrainerPatientsView trainerPatientsView;

    public TrainerPatientsViewModel(Context context, TrainerPatientsView trainerPatientsView) {
        this.context = context;
        this.trainerPatientsView = trainerPatientsView;
    }

    public void getPatient() {
        Observable<TrainerPatientResponse> loginObservable =
                TrainerApiManager.trainerService.getPatients(TrainerActivity.token,TrainerActivity.id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrainerPatientResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TrainerPatientResponse response) {
                        if (response.success)
                            trainerPatientsView.getPatientSuccess(response.data);
                        else
                            trainerPatientsView.getPatientFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        trainerPatientsView.getPatientFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
