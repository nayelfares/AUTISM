package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.GeneralResponse;
import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.api.TrainerApiManager;
import com.medical.autism.trainer.model.ScheduleResponse;
import com.medical.autism.trainer.ui.AddScheduleView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddScheduleViewModel {
    Context context;
    AddScheduleView addScheduleView;

    public AddScheduleViewModel(Context context, AddScheduleView addScheduleView) {
        this.context         = context;
        this.addScheduleView = addScheduleView;
    }

    public void addSchedule(String date, String from, String to) {

        Observable<GeneralResponse> loginObservable =
                TrainerApiManager.trainerService.addSchedule(TrainerActivity.token,TrainerActivity.id,date,from,to);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse response) {
                        if (response.success)
                            addScheduleView.addScheduleSuccess(response.message);
                        else
                            addScheduleView.addScheduleFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        addScheduleView.addScheduleFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
