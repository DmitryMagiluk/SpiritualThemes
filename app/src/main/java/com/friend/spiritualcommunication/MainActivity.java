package com.friend.spiritualcommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Friend;

public class MainActivity extends AppCompatActivity {

    //private ArrayList<Friend> friends;
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
            setTheme(R.style.AppThemeGreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileIdForSharedPref = PreferenceManager.getDefaultSharedPreferences(this).getInt("for_refresh_add_progress_index", -1);
        Log.d("log",profileIdForSharedPref+"");

        //final Friend friend = friends.get()
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
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about){
            try {
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            }catch (Exception exception){
                Toast.makeText(this, "Ошибка 002", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.action_add_note){
            Intent intent = new Intent(this, AddReadNote.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.prayer_needs){
            Intent intent = new Intent(this, PrayerNeeds.class );
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
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_49, Utils.THEME_49, Utils.VERSE_49, Utils.MAIN_TEXT_49));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_50, Utils.THEME_50, Utils.VERSE_50, Utils.MAIN_TEXT_50));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_51, Utils.THEME_51, Utils.VERSE_51, Utils.MAIN_TEXT_51));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_52, Utils.THEME_52, Utils.VERSE_52, Utils.MAIN_TEXT_52));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_53, Utils.THEME_53, Utils.VERSE_53, Utils.MAIN_TEXT_53));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_54, Utils.THEME_54, Utils.VERSE_54, Utils.MAIN_TEXT_54));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_55, Utils.THEME_55, Utils.VERSE_55, Utils.MAIN_TEXT_55));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_56, Utils.THEME_56, Utils.VERSE_56, Utils.MAIN_TEXT_56));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_57, Utils.THEME_57, Utils.VERSE_57, Utils.MAIN_TEXT_57));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_58, Utils.THEME_58, Utils.VERSE_58, Utils.MAIN_TEXT_58));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_59, Utils.THEME_59, Utils.VERSE_59, Utils.MAIN_TEXT_59));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_60, Utils.THEME_60, Utils.VERSE_60, Utils.MAIN_TEXT_60));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_61, Utils.THEME_61, Utils.VERSE_61, Utils.MAIN_TEXT_61));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_62, Utils.THEME_62, Utils.VERSE_62, Utils.MAIN_TEXT_62));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_63, Utils.THEME_63, Utils.VERSE_63, Utils.MAIN_TEXT_63));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_64, Utils.THEME_64, Utils.VERSE_64, Utils.MAIN_TEXT_64));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_65, Utils.THEME_65, Utils.VERSE_65, Utils.MAIN_TEXT_65));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_66, Utils.THEME_66, Utils.VERSE_66, Utils.MAIN_TEXT_66));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_67, Utils.THEME_67, Utils.VERSE_67, Utils.MAIN_TEXT_67));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_68, Utils.THEME_68, Utils.VERSE_68, Utils.MAIN_TEXT_68));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_69, Utils.THEME_69, Utils.VERSE_69, Utils.MAIN_TEXT_69));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_70, Utils.THEME_70, Utils.VERSE_70, Utils.MAIN_TEXT_70));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_71, Utils.THEME_71, Utils.VERSE_71, Utils.MAIN_TEXT_71));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_72, Utils.THEME_72, Utils.VERSE_72, Utils.MAIN_TEXT_72));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_73, Utils.THEME_73, Utils.VERSE_73, Utils.MAIN_TEXT_73));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_74, Utils.THEME_74, Utils.VERSE_74, Utils.MAIN_TEXT_74));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_75, Utils.THEME_75, Utils.VERSE_75, Utils.MAIN_TEXT_75));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_76, Utils.THEME_76, Utils.VERSE_76, Utils.MAIN_TEXT_76));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_77, Utils.THEME_77, Utils.VERSE_77, Utils.MAIN_TEXT_77));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_78, Utils.THEME_78, Utils.VERSE_78, Utils.MAIN_TEXT_78));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_79, Utils.THEME_79, Utils.VERSE_79, Utils.MAIN_TEXT_79));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_80, Utils.THEME_80, Utils.VERSE_80, Utils.MAIN_TEXT_80));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_81, Utils.THEME_81, Utils.VERSE_81, Utils.MAIN_TEXT_81));
        spiritualCommunicationItems.add(new SpiritualCommunicationItem(R.drawable.theme_82, Utils.THEME_82, Utils.VERSE_82, Utils.MAIN_TEXT_82));

    }
}
