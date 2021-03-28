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
import com.medical.autism.parent.model.Appointment;
import com.medical.autism.parent.ui.ParentProfile;
import com.medical.autism.trainer.model.Schedule;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private final ArrayList<Schedule> schedules;
    private final Context             context;

    public ScheduleAdapter(Context context, ArrayList<Schedule> schedules) {
        this.schedules          = schedules;
        this.context            = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.schedule_item_row, viewGroup, false);

        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Schedule schedule = schedules.get(position);
        viewHolder.schedualeRowDay.setText(schedule.day);
        viewHolder.schedualeRowTime.setText(schedule.from+" - "+schedule.to);
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView              schedualeRowDay;
         final TextView              schedualeRowTime;
         final ImageButton           aschedualeRowCancel;
        public ViewHolder(View view) {
            super(view);
            schedualeRowDay        = view.findViewById(R.id.schedualeRowDay);
            schedualeRowTime       = view.findViewById(R.id.schedualeRowTime);
            aschedualeRowCancel     = view.findViewById(R.id.aschedualeRowCancel);
        }

    }

}