package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.api.TrainerApiManager;
import com.medical.autism.trainer.model.ProfileResponse;
import com.medical.autism.trainer.model.ScheduleResponse;
import com.medical.autism.trainer.ui.SchedualesView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SchedualesViewModel {
    Context context;
    SchedualesView schedualesView;

    public SchedualesViewModel(Context context, SchedualesView schedualesView) {
        this.context = context;
        this.schedualesView = schedualesView;
    }

    public void getSchedules() {
        Observable<ScheduleResponse> loginObservable =
                TrainerApiManager.trainerService.getSchedules(TrainerActivity.token,TrainerActivity.id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScheduleResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ScheduleResponse response) {
                        if (response.success)
                            schedualesView.getSchedulesSuccess(response.data);
                        else
                            schedualesView.getSchedulesFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        schedualesView.getSchedulesFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
