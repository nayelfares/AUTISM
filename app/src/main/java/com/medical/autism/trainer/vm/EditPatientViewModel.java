package com.medical.autism.trainer.vm;

import android.content.Context;

import com.medical.autism.GeneralResponse;
import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.api.TrainerApiManager;
import com.medical.autism.trainer.model.ScheduleResponse;
import com.medical.autism.trainer.ui.EditPatientView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditPatientViewModel {
    Context         context;
    EditPatientView editPatientView;

    public EditPatientViewModel(Context context, EditPatientView editPatientView) {
        this.context = context;
        this.editPatientView = editPatientView;
    }

    public void updateMainProblem(String user_id, String mainProblem) {
        Observable<GeneralResponse> loginObservable =
                TrainerApiManager.trainerService.updateMainProblem(TrainerActivity.token,user_id,mainProblem);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse response) {
                        if (response.success)
                            editPatientView.updateMainProblemSuccess(response.message);
                        else
                            editPatientView.updateMainProblemFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        editPatientView.updateMainProblemFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
