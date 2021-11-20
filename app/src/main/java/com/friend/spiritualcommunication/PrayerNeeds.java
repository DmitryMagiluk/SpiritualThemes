package com.friend.spiritualcommunication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class PrayerNeeds extends AppCompatActivity {

    EditText editText;
    ImageButton button;
    Boolean isAdding = false;
    SharedPreferences.Editor editor;
    SharedPreferences noteTextSharedPref;
    String noteText;
    KeyListener keyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            setTheme(R.style.AppThemeGreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_needs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Молитвенные нужды");

        editText = findViewById(R.id.editTextView);
        button = findViewById(R.id.buttonNote);

        keyListener = editText.getKeyListener();
        editText.setKeyListener(null);

        noteTextSharedPref = getSharedPreferences("PRAYER_NEEDS", MODE_PRIVATE);
        noteText = noteTextSharedPref.getString("text_needs", "");
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void saveEditText (){
        editor = noteTextSharedPref.edit();
        editor.putString("text_needs", editText.getText().toString());
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