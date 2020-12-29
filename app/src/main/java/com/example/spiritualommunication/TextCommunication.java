package com.example.spiritualommunication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class TextCommunication extends AppCompatActivity {
    TextView mainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_communication);

        mainTextView = findViewById(R.id.mainTextView);

        Intent intent = getIntent();
        if(intent != null){ // Устанавливаем тему в actionBar , устанавливаем CustomActionBar
            mainTextView.setText(intent.getStringExtra("mainText"));

            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //bellow setSupportActionBar(toolbar);
            getSupportActionBar().setCustomView(R.layout.titlebar);

            TextView title = (TextView) getSupportActionBar().getCustomView()
                    .findViewById(R.id.actionBarTextView);
            title.setText (intent.getStringExtra("theme"));
            //getSupportActionBar().hide();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int textSize = Integer.valueOf(sharedPreferences.getString("text_size","20"));
        mainTextView.setTextSize(textSize);
    }
}