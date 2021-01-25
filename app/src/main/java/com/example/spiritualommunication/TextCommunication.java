package com.example.spiritualommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NavUtils;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import Data.FriendsAppDatabase;
import Model.Friend;

import static com.example.spiritualommunication.ProfilesActivity.PROFILE_THEMES_PROGRESS;
import static com.example.spiritualommunication.ProfilesActivity.THEME_PROGRESS;


public class TextCommunication extends AppCompatActivity {
    TextView mainTextView;
    TextView hintButton;
    TextView themeText;
    Button button; // кнопка для изменения состояния темы
    SharedPreferences mSettings; // состояние темы
    Integer position;
    Integer progress;
    //ConstraintLayout constraintLayout;
    String mainText;
    String theme;
    int textSize; // Размер шрифта
    Integer profileIdForSharedPrefName;
    Intent intent;
    SharedPreferences sharedPreferences; // настройка размера текста


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
        setContentView(R.layout.activity_text_communication);


        mainTextView = findViewById(R.id.mainTextView);
        themeText = findViewById(R.id.themeTextView);
        button = findViewById(R.id.progressButton);
        //constraintLayout = findViewById(R.id.constraintLayout);
        hintButton = findViewById(R.id.hintButton);

        intent = getIntent();
        if(intent != null){ // Устанавливаем тему в actionBar , устанавливаем CustomActionBar
            mainText = intent.getStringExtra("mainText");
            theme = intent.getStringExtra("theme");
            profileIdForSharedPrefName = PreferenceManager.getDefaultSharedPreferences(this).getInt("for_refresh_add_progress_index", -1);
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



        mSettings = getSharedPreferences("PROFILE_THEMES_PROGRESS" + profileIdForSharedPrefName, Context.MODE_PRIVATE);
        //Log.d("profileIdForSharedPref", profileIdForSharedPref+"");
        progress = mSettings.getInt(THEME_PROGRESS + position, 0);

        if(progress == 0){
            doLaterTheme();
        }else if(progress == 1){
            endTheme();
        }else if(progress == 2){
            refreshTheme();
        }
    }

    public void saveSharedPreferences(View view) {

        progress = mSettings.getInt(THEME_PROGRESS + position, 0);
        SharedPreferences.Editor editor = mSettings.edit();
        if(progress == 0){
            endTheme();

            editor.putInt(THEME_PROGRESS + position , 1);
            editor.apply();
            //Toast.makeText(this, "Отмечана как незавершенная", Toast.LENGTH_SHORT).show();
        }else if(progress == 1){
            refreshTheme();

            editor.putInt(THEME_PROGRESS + position , 2);
            editor.apply();
            MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.end_theme);
            mediaPlayer.start();
            //Toast.makeText(this, "Отмечана как завершенная", Toast.LENGTH_SHORT).show();
        }else if(progress == 2){
            doLaterTheme();

            editor.putInt(THEME_PROGRESS + position , 0);
            editor.apply();
            Toast.makeText(this, "Благословений !", Toast.LENGTH_SHORT).show();
        }
    }


    private void endTheme() {
        button.setText("Завершить тему");
        button.setBackgroundResource(R.drawable.customborder_green);
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_done_40, 0, R.drawable.ic_baseline_done_40, 0);
        //constraintLayout.setBackgroundColor(getResources().getColor(white));
        themeText.setText(theme);
        mainTextView.setText(mainText);
        mainTextView.setTextSize(textSize);
        mainTextView.setGravity(Gravity.NO_GRAVITY);
        hintButton.setText("Тема отмечена как незавершенная.\nЕсли вы прошли тему полностью, то нажмите на кнопку 'Завершить тему'.");

    }

    private void refreshTheme() {
        button.setText("Пройти тему заново");
        button.setBackgroundResource(R.drawable.customborder_blue);
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_autorenew_40, 0, R.drawable.ic_baseline_autorenew_40, 0);
        //constraintLayout.setBackgroundColor(getResources().getColor(green));
        mainTextView.setText("Тема пройдена !");
        mainTextView.setTextSize(24);
        mainTextView.setGravity(Gravity.CENTER);
        hintButton.setText("");
    }

    private void doLaterTheme(){
        button.setText("Завершить тему позже");
        button.setBackgroundResource(R.drawable.customborder_yellow);
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_grade_40, 0, R.drawable.ic_baseline_grade_40, 0);
        themeText.setText(theme);
        mainTextView.setText(mainText);
        mainTextView.setTextSize(textSize);
        mainTextView.setGravity(Gravity.NO_GRAVITY);
        hintButton.setText("Нажмите на кнопку 'Завершить тему позже' если вы не успели пройти тему. Если вы прошли тему полностью, то нажмите на кнопку два раза.");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            //NavUtils.navigateUpFromSameTask(this);
            finish();
            return true;
        } else if(id == R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            intent.putExtra("PROFILE_THEMES_PROGRESS",profileIdForSharedPrefName);
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
        super.onStart();
    }
}