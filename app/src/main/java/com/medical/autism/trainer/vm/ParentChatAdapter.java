package com.medical.autism.trainer.vm;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.medical.Util;
import com.medical.autism.ChatWebView;
import com.medical.autism.R;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.model.Trainer;
import com.medical.autism.parent.ui.ParentChat;
import com.medical.autism.trainer.TrainerActivity;
import com.medical.autism.trainer.model.TrainerPatient;
import com.medical.autism.trainer.ui.TrainerChat;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ParentChatAdapter extends RecyclerView.Adapter<ParentChatAdapter.ViewHolder> {

    private final ArrayList<TrainerPatient> parents;
    private final TrainerChat context;

    public ParentChatAdapter(TrainerChat context, ArrayList<TrainerPatient> parents) {
        this.parents = parents;
        this.context  = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.parent_chat_item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        TrainerPatient parent = parents.get(position);
        viewHolder.parentChatChildName.setText(parent.first_name + " " + parent.last_name);
        viewHolder.parentChatChildName.setText(parent.child_name);
        Glide.with(context)
                .load(Util.toUrl(parent.photo))
                .into(viewHolder.parentChatPhoto);
       viewHolder.parentChat.setOnClickListener(v->{
           ((TrainerActivity)context.requireActivity()).replaceFragment(
                   new ChatWebView("https://crm.towarddevelopment.org/api/message?sender_id="+
                           TrainerActivity.id+"&reciver_id="+parent.user_id)
           );
       });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return parents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView             parentChatParentName;
         final TextView               parentChatChildName;
         final CircleImageView      parentChatPhoto;
         final TextView             parentChat;
        public ViewHolder(View view) {
            super(view);
            parentChatParentName    = view.findViewById(R.id.parentChatParentName);
            parentChatChildName     = view.findViewById(R.id.parentChatChildName);
            parentChatPhoto         = view.findViewById(R.id.parentChatPhoto);
            parentChat              = view.findViewById(R.id.parentChat);
        }

    }
}