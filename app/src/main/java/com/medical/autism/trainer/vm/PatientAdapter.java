package com.medical.autism.trainer.vm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.medical.autism.R;
import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.model.TrainerPatient;
import com.medical.autism.trainer.ui.EditPatient;
import com.medical.autism.trainer.ui.TrainerPatients;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    private final ArrayList<TrainerPatient> patients;
    private final TrainerPatients context;

    public PatientAdapter(TrainerPatients context, ArrayList<TrainerPatient> patients) {
        this.patients           = patients;
        this.context            = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.patient_row, viewGroup, false);

        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        TrainerPatient patient = patients.get(position);
        viewHolder.trainerPatientChildName.setText(patient.child_name);
        viewHolder.trainerPatientName.setText(patient.first_name+" "+patient.last_name);
        int endTime=Integer.parseInt(patient.time.substring(0,2))+1;
        if (endTime>=24)
            endTime-=24;
        String endTimeString=String.valueOf(endTime);
        if (endTimeString.length()==1)
            endTimeString="0"+endTimeString+":00:00";
        else
            endTimeString=endTimeString+":00:00";
        viewHolder.trainerPatientTime.setText(patient.day+" , "+patient.time+" - "+endTimeString);
        viewHolder.itemView.setOnClickListener(v->{
            ((TrainerActivity) context.requireActivity()).replaceFragment(new EditPatient(context,patient));
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView              trainerPatientChildName;
         final TextView              trainerPatientName;
         final TextView               trainerPatientTime;
        public ViewHolder(View view) {
            super(view);
            trainerPatientChildName        = view.findViewById(R.id.trainerPatientChildName);
            trainerPatientName       = view.findViewById(R.id.trainerPatientName);
            trainerPatientTime   = view.findViewById(R.id.trainerPatientTime);
        }

    }

}