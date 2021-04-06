package com.friend.spiritualcommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddReadNote extends AppCompatActivity {

    EditText editText;
    ImageButton button;
    Boolean isAdding = false;
    KeyListener keyListener;
    int profileIdForSharedPrefSettings;
    SharedPreferences noteTextSharedPref;
    String noteText;
    SharedPreferences.Editor editor;

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
        setContentView(R.layout.activity_add_read_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.editTextView);
        button = findViewById(R.id.buttonNote);

        keyListener = editText.getKeyListener();
        editText.setKeyListener(null);

        try {
            //editText.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_IN);
            editText.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception exception){
            Toast.makeText(this, "Ошибка белого фона 001", Toast.LENGTH_LONG).show();
        }

        profileIdForSharedPrefSettings = PreferenceManager.getDefaultSharedPreferences(this).getInt("for_refresh_add_progress_index",-1);

        noteTextSharedPref = getSharedPreferences("PROFILE_THEMES_PROGRESS_" + profileIdForSharedPrefSettings, MODE_PRIVATE);
        noteText = noteTextSharedPref.getString("text_note", "");
        editText.setText(noteText);

        saveOrChangeText();
    }

    public void addChangeNote(View view) {
        isAdding = !isAdding;
        saveOrChangeText();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            //NavUtils.navigateUpFromSameTask(this);
            saveEditText();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void saveEditText (){
        editor = noteTextSharedPref.edit();
        editor.putString("text_note", editText.getText().toString());
        editor.apply();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.setKeyListener(null);
        //editText.setFocusableInTouchMode(false);
        //editText.setEnabled(false);
        editText.setCursorVisible(false);
        //editText.setBackgroundColor(Color.TRANSPARENT);
        //editText.setKeyListener(null);
    }

    void changeEditText (){
        editText.setKeyListener(keyListener);
        editText.requestFocus();
        editText.setCursorVisible(true);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        editText.setSelection(editText.getText().length());
    }

    void saveOrChangeText (){
        if (isAdding){
            changeEditText();
            //button.setText("Готово");
            button.setImageResource(R.drawable.ic_baseline_done_40);
        } else {
            saveEditText();
            button.setImageResource(R.drawable.ic_baseline_edit_40);
            //button.setText("Добавить или Изменить");
        }
    }

}