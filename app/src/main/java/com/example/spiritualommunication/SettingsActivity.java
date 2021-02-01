package com.example.spiritualommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import static com.example.spiritualommunication.ProfilesActivity.PROFILE_THEMES_PROGRESS;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int stile = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString("app_color_theme", "1"));
        if (stile == 1){
            setTheme(R.style.AppThemeGreen);
        } else if (stile == 2){
            setTheme(R.style.AppThemeLightBlue);
        } else if (stile == 3){
            setTheme(R.style.AppThemeBlue);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        //Log.d("getProfi",profileIdForSharedPref+"");


        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            //NavUtils.navigateUpFromSameTask(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}