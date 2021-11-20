package com.friend.spiritualcommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class AboutActivity extends AppCompatActivity {

    String[] addressSupport = {"themes-spiritual@mail.ru"};
    String[] addressService = {"spiritual-friend@mail.ru"};
    TextView appNameTextView, textViewDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppThemeGreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        appNameTextView = findViewById(R.id.appName);
        textViewDescription = findViewById(R.id.textViewHint);
        appNameTextView.setText(R.string.title_main_action_bar);
        textViewDescription.setText((String)Utils.ABOUT_APP);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendEmailSupport(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,addressSupport);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void sendEmailService(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,addressService);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}