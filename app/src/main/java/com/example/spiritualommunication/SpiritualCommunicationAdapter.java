package com.example.spiritualommunication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.spiritualommunication.MainActivity.THEMES_PROGRESS;
import static com.example.spiritualommunication.MainActivity.THEME_PROGRESS;

public class SpiritualCommunicationAdapter extends RecyclerView.Adapter<SpiritualCommunicationAdapter.SpiritualCommunicationViewHolder>{

    ArrayList<SpiritualCommunicationItem> arrayList = new ArrayList<>();
    Context context;

    SharedPreferences mSettings;


    public SpiritualCommunicationAdapter(ArrayList<SpiritualCommunicationItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpiritualCommunicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spiritual_communication_item,
                parent, false);
        SpiritualCommunicationViewHolder spiritualCommunicationViewHolder = new SpiritualCommunicationViewHolder(view);
        return spiritualCommunicationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpiritualCommunicationViewHolder holder, int position) {
        SpiritualCommunicationItem spiritualCommunicationItem = arrayList.get(position); // создаем переменную типа класса и записываем в нее содержимое одной позиции массива

        holder.imageRecourse.setImageResource(spiritualCommunicationItem.getImageRecourse());
        holder.theme.setText(spiritualCommunicationItem.getTheme());
        holder.verse.setText(spiritualCommunicationItem.getVerse());

        mSettings = context.getSharedPreferences(THEMES_PROGRESS, Context.MODE_PRIVATE);
        Integer progress = mSettings.getInt(THEME_PROGRESS + position,0);


        if(progress == 0){
            holder.progressImageRecourse.setImageResource(0);
            //holder.progressImageRecourse.setBackgroundColor(0);
        } else if(progress == 1){
            holder.progressImageRecourse.setImageResource(R.drawable.ic_baseline_update_160);
            //holder.progressImageRecourse.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }else if(progress == 2){
            holder.progressImageRecourse.setImageResource(R.drawable.ic_baseline_done_160);
            //holder.progressImageRecourse.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class SpiritualCommunicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageRecourse;
        public ImageView progressImageRecourse;
        public TextView theme;
        public TextView verse;

        public SpiritualCommunicationViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageRecourse = itemView.findViewById(R.id.themeImageView);
            progressImageRecourse = itemView.findViewById(R.id.progressThemeImageView);
            theme = itemView.findViewById(R.id.themeTextView);
            verse = itemView.findViewById(R.id.verseTextView);
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
