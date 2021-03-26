package com.medical.autism.onboarding.vm;

import android.content.Context;

import com.medical.autism.onboarding.api.OnbordingApiManager;
import com.medical.autism.onboarding.model.LoginResponse;
import com.medical.autism.onboarding.ui.RegistrationView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationViewModel {
    public RegistrationView registrationView;
    public Context context;

    public RegistrationViewModel(RegistrationView registrationView, Context context) {
        this.registrationView = registrationView;
        this.context = context;
    }

    public void register(
            String first_name,
            String last_name,
            String user_name,
            String email,
            String password,
            String phone,
            String type,
            String speciality,
            String collage,
            String previous_clincs,
            String clinic_address,
            String experience_years,
            String certificate_number,
            String child_name,
            String child_age,
            String parent_job,
            String marriage_status,
            String parent_gender,
            String child_number,
            String child_main_problem
    ){
        Observable<LoginResponse> loginObservable =
                OnbordingApiManager.onboardingService.register(first_name, last_name, user_name, email, password, phone, type, speciality, collage, previous_clincs, clinic_address, experience_years, certificate_number, child_name, child_age, parent_job, marriage_status, parent_gender, child_number, child_main_problem);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {
                        if (loginResponse.success)
                            registrationView.registrationSuccess(loginResponse.message);
                        else
                            registrationView.registrationFailed(loginResponse.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        registrationView.registrationFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
