package com.medical.autism.parent.vm;

import android.content.Context;
import android.net.Uri;

import com.medical.autism.GeneralResponse;
import com.medical.autism.onboarding.model.LoginResponse;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.api.ParentApiManager;
import com.medical.autism.parent.model.ProfileResponse;
import com.medical.autism.parent.ui.ParentProfileView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    public void updatePhoto(Uri resultUri) {
        File picture = new File(resultUri.getPath());
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"),
                        picture
                );
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", picture.getName(), requestFile);
        Observable<GeneralResponse> loginObservable =
                ParentApiManager.parentService.upload(ParentActivity.token,body,ParentActivity.id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse response) {
                        if (response.success)
                            parentProfileView.updatePhotoSuccess(response.message,resultUri);
                        else
                            parentProfileView.updatePhotoFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        parentProfileView.updatePhotoFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void updateProfile(
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
                ParentApiManager.parentService.updateProfile(ParentActivity.token,ParentActivity.id,first_name, last_name, user_name, email, password, phone, type, speciality, collage, previous_clincs, clinic_address, experience_years, certificate_number, child_name, child_age, parent_job, marriage_status, parent_gender, child_number, child_main_problem);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {
                        if (loginResponse.success)
                            parentProfileView.updateProfileSuccess(loginResponse.message);
                        else
                            parentProfileView.updateProfileFailed(loginResponse.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        parentProfileView.updateProfileFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
