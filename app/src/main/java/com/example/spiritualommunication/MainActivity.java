package com.example.spiritualommunication;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.spiritualommunication.ProfilesActivity.PROFILE_THEMES_PROGRESS;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
//    ThemeAppDatabase themeAppDatabase;
    ArrayList<SpiritualCommunicationItem> themes = new ArrayList<>();

    //final static int n = 46;
    public Integer profileIdForSharedPref;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int stile = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString("app_color_theme", "1"));
        if (stile == 1){
            setTheme(R.style.AppThemeBlue);
        } else if (stile == 2){
            setTheme(R.style.AppThemeLightBlue);
        } else if (stile == 3){
            setTheme(R.style.AppThemeGreen);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileIdForSharedPref = PreferenceManager.getDefaultSharedPreferences(this).getInt("for_refresh_add_progress_index", -1);
        Log.d("log",profileIdForSharedPref+"");

        getSupportActionBar().setTitle(R.string.title_main_action_bar);

        ArrayList<SpiritualCommunicationItem> spiritualCommunicationItems = new ArrayList<>();
        fillArray(spiritualCommunicationItems);


        themes.addAll(spiritualCommunicationItems);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        adapter = new SpiritualCommunicationAdapter(themes,this, profileIdForSharedPref);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            intent.putExtra("PROFILE_THEMES_PROGRESS",profileIdForSharedPref);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_add_note){
            Intent intent = new Intent(this, AddReadNote.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    void fillArray(ArrayList<SpiritualCommunicationItem> spiritualCommunicationItems){
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_1, Utils.THEME_1, Utils.VERSE_1, Utils.MAIN_TEXT_1));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_2, Utils.THEME_2, Utils.VERSE_2, Utils.MAIN_TEXT_2));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_3, Utils.THEME_3, Utils.VERSE_3, Utils.MAIN_TEXT_3));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_4, Utils.THEME_4, Utils.VERSE_4, Utils.MAIN_TEXT_4));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_5, Utils.THEME_5, Utils.VERSE_5, Utils.MAIN_TEXT_5));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_6, Utils.THEME_6, Utils.VERSE_6, Utils.MAIN_TEXT_6));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_7, Utils.THEME_7, Utils.VERSE_7, Utils.MAIN_TEXT_7));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_8, Utils.THEME_8, Utils.VERSE_8, Utils.MAIN_TEXT_8));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_9, Utils.THEME_9, Utils.VERSE_9, Utils.MAIN_TEXT_9));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_10, Utils.THEME_10, Utils.VERSE_10, Utils.MAIN_TEXT_10));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_11, Utils.THEME_11, Utils.VERSE_11, Utils.MAIN_TEXT_11));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_12, Utils.THEME_12, Utils.VERSE_12, Utils.MAIN_TEXT_12));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_13, Utils.THEME_13, Utils.VERSE_13, Utils.MAIN_TEXT_13));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_14, Utils.THEME_14, Utils.VERSE_14, Utils.MAIN_TEXT_14));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_15, Utils.THEME_15, Utils.VERSE_15, Utils.MAIN_TEXT_15));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_16, Utils.THEME_16, Utils.VERSE_16, Utils.MAIN_TEXT_16));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_17, Utils.THEME_17, Utils.VERSE_17, Utils.MAIN_TEXT_17));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_18, Utils.THEME_18, Utils.VERSE_18, Utils.MAIN_TEXT_18));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_19, Utils.THEME_19, Utils.VERSE_19, Utils.MAIN_TEXT_19));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_20, Utils.THEME_20, Utils.VERSE_20, Utils.MAIN_TEXT_20));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_21, Utils.THEME_21, Utils.VERSE_21, Utils.MAIN_TEXT_21));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_22, Utils.THEME_22, Utils.VERSE_22, Utils.MAIN_TEXT_22));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_23, Utils.THEME_23, Utils.VERSE_23, Utils.MAIN_TEXT_23));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_24, Utils.THEME_24, Utils.VERSE_24, Utils.MAIN_TEXT_24));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_25, Utils.THEME_25, Utils.VERSE_25, Utils.MAIN_TEXT_25));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_26, Utils.THEME_26, Utils.VERSE_26, Utils.MAIN_TEXT_26));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_27, Utils.THEME_27, Utils.VERSE_27, Utils.MAIN_TEXT_27));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_28, Utils.THEME_28, Utils.VERSE_28, Utils.MAIN_TEXT_28));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_29, Utils.THEME_29, Utils.VERSE_29, Utils.MAIN_TEXT_29));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_30, Utils.THEME_30, Utils.VERSE_30, Utils.MAIN_TEXT_30));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_31, Utils.THEME_31, Utils.VERSE_31, Utils.MAIN_TEXT_31));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_32, Utils.THEME_32, Utils.VERSE_32, Utils.MAIN_TEXT_32));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_33, Utils.THEME_33, Utils.VERSE_33, Utils.MAIN_TEXT_33));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_34, Utils.THEME_34, Utils.VERSE_34, Utils.MAIN_TEXT_34));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_35, Utils.THEME_35, Utils.VERSE_35, Utils.MAIN_TEXT_35));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_36, Utils.THEME_36, Utils.VERSE_36, Utils.MAIN_TEXT_36));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_37, Utils.THEME_37, Utils.VERSE_37, Utils.MAIN_TEXT_37));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_38, Utils.THEME_38, Utils.VERSE_38, Utils.MAIN_TEXT_38));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_39, Utils.THEME_39, Utils.VERSE_39, Utils.MAIN_TEXT_39));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_40, Utils.THEME_40, Utils.VERSE_40, Utils.MAIN_TEXT_40));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_41, Utils.THEME_41, Utils.VERSE_41, Utils.MAIN_TEXT_41));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_42, Utils.THEME_42, Utils.VERSE_42, Utils.MAIN_TEXT_42));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_43, Utils.THEME_43, Utils.VERSE_43, Utils.MAIN_TEXT_43));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_44, Utils.THEME_44, Utils.VERSE_44, Utils.MAIN_TEXT_44));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_45, Utils.THEME_45, Utils.VERSE_45, Utils.MAIN_TEXT_45));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_46, Utils.THEME_46, Utils.VERSE_46, Utils.MAIN_TEXT_46));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_47, Utils.THEME_47, Utils.VERSE_47, Utils.MAIN_TEXT_47));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_48, Utils.THEME_48, Utils.VERSE_48, Utils.MAIN_TEXT_48));
    }
}