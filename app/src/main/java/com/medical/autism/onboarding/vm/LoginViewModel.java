package com.medical.autism.onboarding.vm;

import android.content.Context;
import com.medical.autism.onboarding.api.OnbordingApiManager;
import com.medical.autism.onboarding.model.Response;
import com.medical.autism.onboarding.ui.LoginView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel {
    Context context;
    LoginView loginView;

    public LoginViewModel(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    public void login(String email,String password){
        Observable<Response> loginObservable =
                OnbordingApiManager.onboardingService.login(email,password);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response response) {
                            loginView.loginSuccess(response.data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginView.loginFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
