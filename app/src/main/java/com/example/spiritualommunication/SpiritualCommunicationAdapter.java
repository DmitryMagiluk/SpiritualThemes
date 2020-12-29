package com.example.spiritualommunication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Theme;

public class SpiritualCommunicationAdapter extends RecyclerView.Adapter<SpiritualCommunicationAdapter.SpiritualCommunicationViewHolder> {

    ArrayList<SpiritualCommunicationItem> arrayList = new ArrayList<>();
    Context context;

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
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    class SpiritualCommunicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageRecourse;
        public TextView theme;
        public TextView verse;

        public SpiritualCommunicationViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageRecourse = itemView.findViewById(R.id.themeImageView);
            theme = itemView.findViewById(R.id.themeTextView);
            verse = itemView.findViewById(R.id.verseTextView);
        }



        @Override
        public void onClick(View view) {

            SpiritualCommunicationItem spiritualCommunicationItem = arrayList.get(getAdapterPosition());

            Intent intent = new Intent(context,TextCommunication.class);
            intent.putExtra("theme", spiritualCommunicationItem.getTheme());
            intent.putExtra("mainText", spiritualCommunicationItem.getMainText());

            context.startActivity(intent);

        }
    }
}
