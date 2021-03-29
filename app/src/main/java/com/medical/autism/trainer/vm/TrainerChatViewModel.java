package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.api.TrainerApiManager;
import com.medical.autism.trainer.model.TrainerPatientResponse;
import com.medical.autism.trainer.ui.TrainerChatView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrainerChatViewModel {
    Context context;
    TrainerChatView trainerChatView;

    public TrainerChatViewModel(Context context, TrainerChatView trainerChatView) {
        this.context = context;
        this.trainerChatView = trainerChatView;
    }

    public void getParents() {
        Observable<TrainerPatientResponse> loginObservable =
                TrainerApiManager.trainerService.getParents(TrainerActivity.token);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrainerPatientResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TrainerPatientResponse response) {
                        if (response.success)
                            trainerChatView.getParentsSuccess(response.data);
                        else
                            trainerChatView.getParentsFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        trainerChatView.getParentsFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
