package com.medical.autism.trainer.vm;

import android.content.Context;
import android.net.Uri;
import com.medical.autism.GeneralResponse;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.api.TrainerApiManager;
import com.medical.autism.trainer.model.ProfileResponse;
import com.medical.autism.trainer.ui.TrainerProfileView;

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

public class TrainerProfileViewModel {
    Context context;
    TrainerProfileView trainerProfileView;

    public TrainerProfileViewModel(Context context, TrainerProfileView trainerProfileView) {
        this.context = context;
        this.trainerProfileView = trainerProfileView;
    }

    public void getProfile() {
        Observable<ProfileResponse> loginObservable =
                TrainerApiManager.trainerService.getProfile(TrainerActivity.token,TrainerActivity.id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ProfileResponse response) {
                        if (response.success)
                            trainerProfileView.getProfileSuccess(response.data);
                        else
                            trainerProfileView.getProfileFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        trainerProfileView.getProfileFailed(e.getMessage());
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
                TrainerApiManager.trainerService.upload(TrainerActivity.token,body,TrainerActivity.id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse response) {
                        if (response.success)
                            trainerProfileView.updatePhotoSuccess(response.message,resultUri);
                        else
                            trainerProfileView.updatePhotoFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        trainerProfileView.updatePhotoFailed(e.getMessage());
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
        Observable<GeneralResponse> loginObservable =
                TrainerApiManager.trainerService.updateProfile(TrainerActivity.token,TrainerActivity.id,first_name, last_name, user_name, email, password, phone, type, speciality, collage, previous_clincs, clinic_address, experience_years, certificate_number, child_name, child_age, parent_job, marriage_status, parent_gender, child_number, child_main_problem);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse loginResponse) {
                        if (loginResponse.success)
                            trainerProfileView.updateProfileSuccess(loginResponse.message);
                        else
                            trainerProfileView.updateProfileFailed(loginResponse.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        trainerProfileView.updateProfileFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
