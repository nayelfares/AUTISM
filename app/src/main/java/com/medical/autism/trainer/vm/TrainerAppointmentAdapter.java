package com.medical.autism.trainer.vm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.autism.GeneralResponse;
import com.medical.autism.R;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.api.ParentApiManager;
import com.medical.autism.trainer.model.Schedule;
import com.medical.autism.trainer.model.TrainerAppointment;
import com.medical.autism.trainer.ui.TrainerAppointments;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrainerAppointmentAdapter extends RecyclerView.Adapter<TrainerAppointmentAdapter.ViewHolder> {

    private final ArrayList<TrainerAppointment> appointments;
    private final TrainerAppointments             context;

    public TrainerAppointmentAdapter(TrainerAppointments context, ArrayList<TrainerAppointment> appointments) {
        this.appointments       = appointments;
        this.context            = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trainer_appointment_row, viewGroup, false);

        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        TrainerAppointment appointment = appointments.get(position);
        viewHolder.appointmentChildName.setText(appointment.child);
        viewHolder.appointmentParentName.setText(appointment.parent);
        int endTime=Integer.parseInt(appointment.time.substring(0,2))+1;
        if (endTime>=24)
            endTime-=24;
        String endTimeString=String.valueOf(endTime);
        if (endTimeString.length()==1)
            endTimeString="0"+endTimeString+":00:00";
        else
            endTimeString=endTimeString+":00:00";
        viewHolder.appointmentParentTime.setText(appointment.time+" - "+endTimeString);
        viewHolder.appointmentCancel.setOnClickListener(v->{
            deleteAppointment(appointment.appointment_id,position);
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView              appointmentChildName;
         final TextView              appointmentParentName;
         final TextView               appointmentParentTime;
         final ImageButton           appointmentCancel;
        public ViewHolder(View view) {
            super(view);
            appointmentChildName        = view.findViewById(R.id.appointmentChildName);
            appointmentParentName       = view.findViewById(R.id.appointmentParentName);
            appointmentParentTime   = view.findViewById(R.id.appointmentParentTime);
            appointmentCancel           = view.findViewById(R.id.appointmentCancel);
        }

    }

    public void deleteAppointment(Integer id,int position) {
        context.loading();
        Observable<GeneralResponse> loginObservable =
                ParentApiManager.parentService.deleteAppointment(ParentActivity.token,id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull GeneralResponse response) {
                        if (response.success ){
                            appointments.remove(position);
                            notifyItemRemoved(position);
                            notifyDataSetChanged();
                            context.stopLoading();
                            context.showMessage(response.message);
                        }
                        else {
                            context.stopLoading();
                            context.showMessage(response.message);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        context.stopLoading();
                        context.showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}