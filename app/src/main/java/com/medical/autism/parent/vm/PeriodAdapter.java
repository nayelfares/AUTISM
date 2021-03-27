package com.medical.autism.parent.vm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.medical.autism.R;
import com.medical.autism.parent.model.Period;
import com.medical.autism.parent.ui.Appointments;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.ViewHolder> {

    private final Period[] periods;
    private final Appointments appointments;

    public PeriodAdapter(Appointments appointments, Period[] periods) {
        this.periods       = periods;
        this.appointments  = appointments;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.period_item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Period period = periods[position];
        viewHolder.startTime.setText(period.time);
        viewHolder.endTime.setText(period.end_time);
        if (period.selected){
            viewHolder.itemView.setBackground(appointments.getResources().getDrawable(R.drawable.ic_border_selected));
        }else{
            viewHolder.itemView.setBackground(appointments.getResources().getDrawable(R.drawable.ic_border));
        }
        viewHolder.itemView.setOnClickListener(v->{
            if (!period.booked) {
                for (int i = 0; i < periods.length; i++) {
                    if (periods[i].selected) {
                        periods[i].selected = false;
                        notifyItemChanged(i);
                    }
                }
                periods[position].selected = true;
                notifyItemChanged(position);
                appointments.selectTime(period.time,period.schedule_id);
            }
        });
        if (period.booked) {
            viewHolder.notAvailable.setVisibility(View.VISIBLE);
        }else{
            viewHolder.notAvailable.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return periods.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView             startTime;
         final TextView             endTime;
        final TextView              notAvailable;
        public ViewHolder(View view) {
            super(view);
            startTime     = view.findViewById(R.id.periodStartTime);
            endTime       = view.findViewById(R.id.periodEndTime);
            notAvailable  = view.findViewById(R.id.notAvailable);
        }

    }
}