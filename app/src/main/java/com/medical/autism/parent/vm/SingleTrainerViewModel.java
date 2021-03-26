package com.medical.autism.parent.vm;

import android.content.Context;

import com.medical.autism.GeneralResponse;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.api.ParentApiManager;
import com.medical.autism.parent.model.GetTrainersResponse;
import com.medical.autism.parent.ui.SingleTrainerView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SingleTrainerViewModel {
    Context context;
    SingleTrainerView singleTrainerView;

    public SingleTrainerViewModel(Context context, SingleTrainerView singleTrainerView) {
        this.context = context;
        this.singleTrainerView = singleTrainerView;
    }

    public void review(Integer trainer_id,Float value){
        Observable<GeneralResponse> reviewObservable =
                ParentApiManager.parentService.review(ParentActivity.token,trainer_id.toString(),
                        ParentActivity.id.toString(),value.toString());
        reviewObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse response) {
                        if (response.success)
                            singleTrainerView.reviewSuccess(response.message);
                        else
                            singleTrainerView.reviewFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        singleTrainerView.reviewFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
