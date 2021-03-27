package com.medical.autism.parent.vm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.autism.BaseFragment;
import com.medical.autism.GeneralResponse;
import com.medical.autism.R;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.api.ParentApiManager;
import com.medical.autism.parent.model.Appointment;
import com.medical.autism.parent.model.Period;
import com.medical.autism.parent.ui.Appointments;
import com.medical.autism.parent.ui.ParentProfile;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private final ArrayList<Appointment> appointments;
    private final ParentProfile context;

    public AppointmentAdapter(ParentProfile context, ArrayList<Appointment> appointments) {
        this.appointments       = appointments;
        this.context            = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.appointment_item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Appointment appointment = appointments.get(position);
        viewHolder.appointmentDrName.setText(appointment.first_name+" "+appointment.last_name);
        int lastTime =Integer.parseInt(appointment.time.substring(0,2))+1;
        String lastTimeString = lastTime+":00:00";
        if (lastTime<10)
            lastTimeString = "0"+lastTime+":00:00";

        viewHolder.appointmentTime  .setText(appointment.time+" - "+lastTimeString);
        viewHolder.appointmentCancel.setOnClickListener(v->{
            context.loading();
            deleteAppointment(appointment.appointment_id,position);
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView              appointmentDrName;
         final TextView              appointmentTime;
         final ImageButton           appointmentCancel;
        public ViewHolder(View view) {
            super(view);
            appointmentDrName     = view.findViewById(R.id.appointmentDrName);
            appointmentTime       = view.findViewById(R.id.appointmentTime);
            appointmentCancel     = view.findViewById(R.id.appointmentCancel);
        }

    }

    public void deleteAppointment(Integer id,int position) {
        Observable<GeneralResponse> loginObservable =
                ParentApiManager.parentService.deleteAppointment(ParentActivity.token,id);
        loginObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GeneralResponse response) {
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
                    public void onError(@NonNull Throwable e) {
                        context.stopLoading();
                        context.showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}