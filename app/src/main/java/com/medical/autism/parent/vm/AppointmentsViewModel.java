package com.medical.autism.parent.vm;

import android.content.Context;

import com.medical.autism.GeneralResponse;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.api.ParentApiManager;
import com.medical.autism.parent.model.GetTrainersResponse;
import com.medical.autism.parent.model.PeriodsResponse;
import com.medical.autism.parent.ui.AppointmentsView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppointmentsViewModel {
    Context          context;
    AppointmentsView appointmentsView;

    public AppointmentsViewModel(Context context, AppointmentsView appointmentsView) {
        this.context          = context;
        this.appointmentsView = appointmentsView;
    }

    public void getPeriods(Integer trainerID, String day) {
        Observable<PeriodsResponse> loginObservable =
                ParentApiManager.parentService.getPeriods(ParentActivity.token,trainerID,day);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PeriodsResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PeriodsResponse response) {
                        if (response.success && response.data.length>0)
                            appointmentsView.getPeriodsSuccess(response.data);
                        else
                            appointmentsView.getPeriodsFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        appointmentsView.getPeriodsFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAppointment(Integer schedule_id, String time) {
        Observable<GeneralResponse> loginObservable =
                ParentApiManager.parentService.getAppointment(ParentActivity.token,schedule_id,ParentActivity.id,time);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse response) {
                        if (response.success )
                            appointmentsView.getAppointmentSuccess(response.message);
                        else
                            appointmentsView.getAppointmentFailed(response.message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        appointmentsView.getAppointmentFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
