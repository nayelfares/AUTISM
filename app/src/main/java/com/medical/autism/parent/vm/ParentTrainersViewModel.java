package com.medical.autism.parent.vm;

import android.content.Context;

import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.api.ParentApiManager;
import com.medical.autism.parent.model.GetTrainersResponse;
import com.medical.autism.parent.ui.ParentTrainersView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ParentTrainersViewModel {
    private ParentTrainersView parentTrainersView;
    private Context context;

    public ParentTrainersViewModel(ParentTrainersView parentTrainersView, Context context) {
        this.parentTrainersView = parentTrainersView;
        this.context = context;
    }

    public void getTrainers(){
        Observable<GetTrainersResponse> loginObservable =
                ParentApiManager.parentService.getTrainers(ParentActivity.token);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetTrainersResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetTrainersResponse response) {
                        if (response.success)
                            parentTrainersView.getTrainersSuccess(response.data);
                        else
                            parentTrainersView.getTrainersFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        parentTrainersView.getTrainersFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
