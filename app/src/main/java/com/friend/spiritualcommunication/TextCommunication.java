package com.friend.spiritualcommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.friend.spiritualcommunication.ProfilesActivity.THEME_PROGRESS;


public class TextCommunication extends AppCompatActivity {
    TextView mainTextView;
    TextView themeText;
    ImageButton buttonDone, buttonLater, buttonRefresh; // кнопка для изменения состояния темы
    SharedPreferences mSettings; // состояние темы
    Integer position;
    Integer progress;
    //ConstraintLayout constraintLayout;
    String mainText;
    String theme;
    int textSize; // Размер шрифта
    Integer profileIdForSharedPrefIndex;
    Intent intent;
    SharedPreferences sharedPreferences; // настройка размера текста
    final static int ANIMATE_DURATION = 1000;


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
        setContentView(R.layout.activity_text_communication);


        mainTextView = findViewById(R.id.mainTextView);
        themeText = findViewById(R.id.themeTextView);
        buttonDone = findViewById(R.id.progressButtonDone);
        buttonLater = findViewById(R.id.progressButtonLater);
        buttonRefresh = findViewById(R.id.progressButtonRefresh);
        //constraintLayout = findViewById(R.id.constraintLayout);

        intent = getIntent();
        if(intent != null){ // Устанавливаем тему в actionBar , устанавливаем CustomActionBar
            mainText = intent.getStringExtra("mainText");
            theme = intent.getStringExtra("theme");
            profileIdForSharedPrefIndex = PreferenceManager.getDefaultSharedPreferences(this).getInt("for_refresh_add_progress_index", -1);
            //Log.d("profileIdForSharedPref",profileIdForSharedPref+"");

            position = getIntent().getExtras().getInt("position");
            int numberTheme = position + 1;
            getSupportActionBar().setTitle("Тема " + numberTheme);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            themeText.setText(theme);
            mainTextView.setText(mainText);
            //getSupportActionBar().hide();
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        textSize = Integer.valueOf(sharedPreferences.getString("text_size","20"));
        mainTextView.setTextSize(textSize);



        mSettings = getSharedPreferences("PROFILE_THEMES_PROGRESS" + profileIdForSharedPrefIndex, Context.MODE_PRIVATE);
        //Log.d("profileIdForSharedPref", profileIdForSharedPref+"");
        progress = mSettings.getInt(THEME_PROGRESS + position, 0);

        startButtonTextSettings();

    }

    void startButtonTextSettings(){
        progress = mSettings.getInt(THEME_PROGRESS + position, 0);
        if(progress == 0){
            doTheme();
            buttonLaterAppear(0);
            buttonDoneAppear(0);
            buttonRefreshDisappear(0);
        }else if(progress == 1){
            doLaterTheme();
            buttonLaterDisappear(0);
            buttonRefreshDisappear(0);
        }else if(progress == 2){
            endTheme();
            buttonDoneDisappear(0);
            buttonLaterDisappear(0);
            buttonRefreshAppear(0);
        }
    }

    public void saveSharedPreferencesLater(View view) {

        progress = mSettings.getInt(THEME_PROGRESS + position, 0);
        SharedPreferences.Editor editor = mSettings.edit();
        if(progress == 0) {
            doLaterTheme();
            buttonLaterDisappear(ANIMATE_DURATION);
            editor.putInt(THEME_PROGRESS + position, 1);
            editor.apply();
            Toast.makeText(this, "Отмечена как незавершенная", Toast.LENGTH_SHORT).show();
        }

    }

    public void saveSharedPreferencesDone(View view) {
        progress = mSettings.getInt(THEME_PROGRESS + position, 0);
        SharedPreferences.Editor editor = mSettings.edit();
        if(progress == 1 || progress == 0){
            endTheme();

            buttonLaterDisappear(ANIMATE_DURATION);
            buttonDoneDisappear(ANIMATE_DURATION);
            buttonRefreshAppear(ANIMATE_DURATION);

            editor.putInt(THEME_PROGRESS + position , 2);
            editor.apply();

            Boolean soundMode = Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("sound_mode",false));
            if (!soundMode){
                MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.end_theme);
                mediaPlayer.start();
            }
            //Toast.makeText(this, "Отмечена как завершенная", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveSharedPreferencesRefresh(View view) {
        progress = mSettings.getInt(THEME_PROGRESS + position, 0);
        SharedPreferences.Editor editor = mSettings.edit();
        if(progress == 2){
            doTheme();

            buttonRefreshDisappear(ANIMATE_DURATION);
            buttonDoneAppear(ANIMATE_DURATION);
            buttonLaterAppear(ANIMATE_DURATION);

            editor.putInt(THEME_PROGRESS + position , 0);
            editor.apply();
            Toast.makeText(this, "Благословений!", Toast.LENGTH_SHORT).show();
        }
    }

    private void doLaterTheme() {
        //constraintLayout.setBackgroundColor(getResources().getColor(white));
        themeText.setText(theme);
        mainTextView.setText(mainText);
        mainTextView.setGravity(Gravity.NO_GRAVITY);
        mainTextView.setTextSize(textSize);
    }

    private void endTheme() {
        //constraintLayout.setBackgroundColor(getResources().getColor(green));
        mainTextView.setText("Тема пройдена!");
        mainTextView.setTextSize(20);
        mainTextView.setGravity(Gravity.CENTER);
    }

    private void doTheme(){
        themeText.setText(theme);
        mainTextView.setTextSize(textSize);
        mainTextView.setText(mainText);
        mainTextView.setGravity(Gravity.NO_GRAVITY);
    }

    void buttonLaterDisappear(int duration){
        buttonLater.animate().scaleX(0).scaleY(0).setDuration(duration);
        buttonLater.setClickable(false);
    }

    void buttonLaterAppear(int duration){
        buttonLater.animate().scaleX(1).scaleY(1).setDuration(duration);
        buttonLater.setClickable(true);
    }

    void buttonDoneDisappear(int duration){
        buttonDone.animate().scaleX(0).scaleY(0).setDuration(duration);
        buttonDone.setClickable(false);
    }

    void buttonDoneAppear(int duration){
        buttonDone.animate().scaleX(1).scaleY(1).setDuration(duration);
        buttonDone.setClickable(true);
        buttonDone.bringToFront();
    }

    void buttonRefreshDisappear(int duration){
        buttonRefresh.animate().scaleX(0).scaleY(0).setDuration(duration);
        buttonRefresh.setClickable(false);

    }

    void buttonRefreshAppear(int duration){
        buttonRefresh.animate().scaleX(1).scaleY(1).setDuration(duration);
        buttonRefresh.setClickable(true);
        buttonRefresh.bringToFront();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            //NavUtils.navigateUpFromSameTask(this);
            finish();
            return true;
        } else if(id == R.id.prayer_needs){
                Intent intent = new Intent(this, PrayerNeeds.class );
                startActivity(intent);
                return true;
        } else if(id == R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            //intent.putExtra("PROFILE_THEMES_PROGRESS",profileIdForSharedPrefIndex);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_add_note){
            Intent intent = new Intent(this, AddReadNote.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_for_themes, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        textSize = Integer.valueOf(sharedPreferences.getString("text_size","20"));
        mainTextView.setTextSize(textSize);
        startButtonTextSettings();
        super.onStart();
    }
}