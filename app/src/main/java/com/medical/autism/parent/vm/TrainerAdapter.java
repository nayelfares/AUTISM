package com.medical.autism.parent.vm;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.medical.Util;
import com.medical.autism.R;
import com.medical.autism.parent.ParentActivity;
import com.medical.autism.parent.model.Trainer;
import com.medical.autism.parent.ui.SingleTrainer;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.ViewHolder> {

    private final Trainer[] trainers;
    private final Context   context;

    public TrainerAdapter(Context context,Trainer[] trainers) {
        this.trainers = trainers;
        this.context  = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trainer_item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Trainer trainer = trainers[position];
        viewHolder.name.setText(trainer.first_name+" "+trainer.last_name);
        viewHolder.location.setText(trainer.clinic_address);
        Glide.with(context)
                .load(Util.toUrl(trainer.photo))
                .into(viewHolder.image);
        Log.e("url",Util.toUrl(trainer.photo));
        viewHolder.more.setOnClickListener(v->{
            PopupMenu popup = new PopupMenu(context, viewHolder.more);
            //inflating menu from xml resource
            popup.inflate(R.menu.trainer_options);
            //adding click listener
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.viewTrainer: {
                        ((ParentActivity) context).replaceFragment(new SingleTrainer(trainer));
                        return true;
                    }
                    case R.id.viewAppointments: {
                        //handle menu2 click
                        return true;
                    }
                    default:
                        return false;
                }
            });
            //displaying the popup
            popup.show();
        });
        viewHolder.rating.setRating(trainer.review);
        viewHolder.rating.setIsIndicator(true);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trainers.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         final TextView             name;
         final AppCompatImageButton more;
         final CircleImageView      image;
         final TextView             location;
         final RatingBar            rating;
        public ViewHolder(View view) {
            super(view);
            name     = view.findViewById(R.id.row_trainerName);
            more     = view.findViewById(R.id.row_trainerMore);
            image    = view.findViewById(R.id.row_trainerImage);
            location = view.findViewById(R.id.row_trainerLocation);
            rating   = view.findViewById(R.id.rating);
        }

    }
}