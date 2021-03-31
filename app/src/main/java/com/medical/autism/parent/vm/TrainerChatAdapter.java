package com.medical.autism.parent.vm;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.medical.Util;
import com.medical.autism.ChatWebView;
import com.medical.autism.R;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.model.Trainer;
import com.medical.autism.parent.ui.Appointments;
import com.medical.autism.parent.ui.ParentChat;
import com.medical.autism.parent.ui.SingleTrainer;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrainerChatAdapter extends RecyclerView.Adapter<TrainerChatAdapter.ViewHolder> {

    private final Trainer[] trainers;
    private final ParentChat context;

    public TrainerChatAdapter(ParentChat context, Trainer[] trainers) {
        this.trainers = trainers;
        this.context  = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trainer_chat_item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Trainer trainer = trainers[position];
        viewHolder.trainerChatName.setText(trainer.first_name + " " + trainer.last_name);
        viewHolder.trainerChatSpecialization.setText(trainer.speciality);
        Glide.with(context)
                .load(Util.toUrl(trainer.photo))
                .into(viewHolder.trainerChatPhoto);
       viewHolder.trainerChat.setOnClickListener(v->{
           ((ParentActivity)context.requireActivity()).replaceFragment(
                   new ChatWebView("https://crm.towarddevelopment.org/api/message?sender_id="+
                           ParentActivity.id+"&reciver_id="+trainer.id)
           );
       });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trainers.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView             trainerChatName;
         final TextView               trainerChat;
         final CircleImageView      trainerChatPhoto;
         final TextView             trainerChatSpecialization;
        public ViewHolder(View view) {
            super(view);
            trainerChatName           = view.findViewById(R.id.trainerChatName);
            trainerChat               = view.findViewById(R.id.trainerChat);
            trainerChatPhoto          = view.findViewById(R.id.trainerChatPhoto);
            trainerChatSpecialization = view.findViewById(R.id.trainerChatSpecialization);
        }

    }
}