package com.medical.autism.parent.vm;

import android.content.Context;

import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.api.ParentApiManager;
import com.medical.autism.parent.model.GetTrainersResponse;
import com.medical.autism.parent.model.ProfileResponse;
import com.medical.autism.parent.ui.ParentProfileView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ParentProfileViewModel {
    Context context;
    ParentProfileView parentProfileView;

    public ParentProfileViewModel(Context context, ParentProfileView parentProfileView) {
        this.context = context;
        this.parentProfileView = parentProfileView;
    }

    public void getProfile() {
        Observable<ProfileResponse> loginObservable =
                ParentApiManager.parentService.getProfile(ParentActivity.token,ParentActivity.id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ProfileResponse response) {
                        if (response.success)
                            parentProfileView.getProfileSuccess(response.data);
                        else
                            parentProfileView.getProfileFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        parentProfileView.getProfileFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
