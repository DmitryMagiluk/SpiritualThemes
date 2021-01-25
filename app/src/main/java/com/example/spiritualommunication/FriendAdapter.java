package com.example.spiritualommunication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

import Data.FriendsAppDatabase;
import Model.Friend;

import static com.example.spiritualommunication.ProfilesActivity.PROFILE_THEMES_PROGRESS;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Friend> friends;
    private ProfilesActivity profilesActivity;

    public FriendAdapter(Context context, ArrayList<Friend> friends, ProfilesActivity profilesActivity) {
        this.context = context;
        this.friends = friends;
        this.profilesActivity = profilesActivity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView noteTextView;
        public RelativeLayout iconImageViewRL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            noteTextView = itemView.findViewById(R.id.noteTextView);
            iconImageViewRL = itemView.findViewById(R.id.iconImageViewRL);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Friend friend = friends.get(position);

        holder.nameTextView.setText(friend.getName());
        holder.noteTextView.setText(friend.getNote());

        holder.iconImageViewRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilesActivity.addAndEditFriend(true, friend, position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int index = friend.getIndexingSharedPrefProgress();

                PreferenceManager.getDefaultSharedPreferences(context).edit()
                        .putInt("for_refresh_add_progress_index", index)
                        .apply();

                Intent intent = new Intent(context, MainActivity.class);
                //Log.d("position",position+"");

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
