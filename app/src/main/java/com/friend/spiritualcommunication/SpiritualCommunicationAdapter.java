package com.friend.spiritualcommunication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.friend.spiritualcommunication.ProfilesActivity.THEME_PROGRESS;


public class SpiritualCommunicationAdapter extends RecyclerView.Adapter<SpiritualCommunicationAdapter.SpiritualCommunicationViewHolder>{

    ArrayList<SpiritualCommunicationItem> arrayList = new ArrayList<>();
    Context context;
    Integer profileIdForSharedPref;

    SharedPreferences mSettings;

    public SpiritualCommunicationAdapter(ArrayList<SpiritualCommunicationItem> arrayList, Context context, Integer profileIdForSharedPref) {
        this.arrayList = arrayList;
        this.context = context;
        this.profileIdForSharedPref = profileIdForSharedPref;
    }

    @NonNull
    @Override
    public SpiritualCommunicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spiritual_communication_item, parent, false);

        SpiritualCommunicationViewHolder spiritualCommunicationViewHolder = new SpiritualCommunicationViewHolder(view);
        return spiritualCommunicationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpiritualCommunicationViewHolder holder, int position) {
        SpiritualCommunicationItem spiritualCommunicationItem = arrayList.get(position); // создаем переменную типа класса и записываем в нее содержимое одной позиции массива

        mSettings = context.getSharedPreferences("PROFILE_THEMES_PROGRESS" + profileIdForSharedPref, Context.MODE_PRIVATE);
        int progress = mSettings.getInt(THEME_PROGRESS + position,0);

            holder.imageRecourse.setImageResource(spiritualCommunicationItem.getImageRecourse()); // удалить после отладки................................
            holder.theme.setText(spiritualCommunicationItem.getTheme());
            if(progress == 0){
                holder.imageRight.setBackgroundColor(0);

            } else if(progress == 1){
                holder.imageRight.setBackgroundColor(context.getResources().getColor(R.color.yellow));

            }else if(progress == 2){
                holder.imageRight.setBackgroundColor(context.getResources().getColor(R.color.green));
            }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class SpiritualCommunicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageRecourse;
        public TextView theme;
        public ImageView imageRight;

        public SpiritualCommunicationViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageRecourse = itemView.findViewById(R.id.themeImageView);
            theme = itemView.findViewById(R.id.themeTextView);
            imageRight = itemView.findViewById(R.id.imageRight);
        }



        @Override
        public void onClick(View view) {

            SpiritualCommunicationItem spiritualCommunicationItem = arrayList.get(getAdapterPosition());

            Intent intent = new Intent(context,TextCommunication.class);
            intent.putExtra("theme", spiritualCommunicationItem.getTheme());
            intent.putExtra("mainText", spiritualCommunicationItem.getMainText());
            intent.putExtra("position", getAdapterPosition());
            //Log.d("position", "" + getAdapterPosition());

            context.startActivity(intent);



        }

    }
}
