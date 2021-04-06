package com.friend.spiritualcommunication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Data.FriendsAppDatabase;
import Model.Friend;


public class ProfilesActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    private ArrayList<Friend> friends = new ArrayList<>();
    private RecyclerView recyclerView;
    private FriendsAppDatabase friendsAppDatabase;
    private FriendAdapter friendAdapter;
    private TextView clickTextView;
    private String bufNote = "";

    public final static String PROFILE_THEMES_PROGRESS = "profile_themes_progress";
    public final static String THEME_PROGRESS = "theme_progress ";

    public static final String PREF_KEY_FIRST_START = "com.heinrichreimersoftware.materialintro.demo.PREF_KEY_FIRST_START";
    public static final int REQUEST_CODE_INTRO = 1;

    public static final String PREF_KEY_PROFILE_PROGRESS = "profile_progress_indexing";

    public static final String PREF_KEY_FIRST_CLICK = "first_click";

    @SuppressLint({"WrongConstant", "ResourceAsColor"})
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

        setContentView(R.layout.activity_profiles);
        //Intent intent = new Intent(this,MainIntroActivity.class);
        //startActivityForResult(intent, 1);
//        View viewActionBar = getLayoutInflater().inflate(R.layout.titlebar, null);
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.MATCH_PARENT,
//                Gravity.CENTER);
//        getSupportActionBar().setCustomView(viewActionBar,params);



        boolean firstStart = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(PREF_KEY_FIRST_START, true);
        final boolean firstClick = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getBoolean(PREF_KEY_FIRST_CLICK, true);

        clickTextView = findViewById(R.id.clickTextView);
        if(firstClick){
            clickTextView.setText("Нажмите на кнопку \n  чтобы добавить друга -->");
        }

        recyclerView = findViewById(R.id.recyclerViewFriend);
        friendsAppDatabase = Room.databaseBuilder(getApplicationContext(),
                FriendsAppDatabase.class, "friendsDB").allowMainThreadQueries().build();

        friends.addAll(friendsAppDatabase.getFriendDAO().detAllFriends());

        friendAdapter = new FriendAdapter(this, friends,ProfilesActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(friendAdapter);


        if (firstStart) {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
            .putInt(PREF_KEY_PROFILE_PROGRESS, 2)
            .apply();
            addFriend("Введите Имя друга 1", "Нажмите на карандаш в правом верхнем углу", 1);
            addFriend("Введите Имя друга 2", "Нажмите на карандаш в правом верхнем углу", 2);
            Intent intent = new Intent(this, MainIntroActivity.class);
            startActivityForResult(intent, REQUEST_CODE_INTRO);
        }


        fab = (FloatingActionButton) findViewById(R.id.fab);

        if (stile == 1){
            fab.setBackgroundTintList(getResources().getColorStateList(R.color.green_));

        } else if (stile == 2){
            fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_blue));

        } else if (stile == 3){
            fab.setBackgroundTintList(getResources().getColorStateList(R.color.blue));

        }
        //getSupportActionBar().setTitle(R.string.title_profiles_action_bar);
        //getSupportActionBar().hide();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.titlebar);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstClick){
                    clickTextView.setText("");
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                            .putBoolean(PREF_KEY_FIRST_CLICK, false).apply();
                }
                fab.animate().scaleY(0).setDuration(400);
                fab.animate().scaleX(0).setDuration(400);
                addAndEditFriend(false, null, -1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_INTRO){
            if(resultCode == RESULT_OK){
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean(PREF_KEY_FIRST_START, false)
                        .apply();
            }

        }
    }

//    public void mainActivityStart(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }

    public void addAndEditFriend(final boolean isUpdate, final Friend friend, final int position){

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.layout_add_delete_change_friend, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(ProfilesActivity.this);
        alertDialogBuilderUserInput.setView(view);

        TextView titleTextView = view.findViewById(R.id.titleTextView);
        final EditText nameEditText = (EditText) view.findViewById(R.id.nameEditText);
        final EditText noteEditText = (EditText) view.findViewById(R.id.noteEditText);

        noteEditText.setBackgroundResource(R.drawable.edit_text_normal);
        nameEditText.setBackgroundResource(R.drawable.edit_text_normal);

        titleTextView.setText(!isUpdate ? "Добавьте духовного друга" : "Удалить или изменить данные");

        if(isUpdate && friend!=null){
            nameEditText.setText(friend.getName());
            bufNote = (String) friend.getNote();
            noteEditText.setText("");
        }

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(isUpdate ? "Изменить" : "Добавить" , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton(isUpdate ? "Удалить" : "Назад",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                if(isUpdate && friend!=null){
                                    SharedPreferences sharedPreferences;
                                    sharedPreferences = getApplicationContext().getSharedPreferences("PROFILE_THEMES_PROGRESS" + friend.getIndexingSharedPrefProgress(), MODE_PRIVATE);
                                    sharedPreferences.edit().clear().apply();
                                    Log.d("log",friend.getIndexingSharedPrefProgress()+"");
                                    deleteFriend(friend,position);

                                    Toast.makeText(getApplicationContext(), "Удаление завершено", Toast.LENGTH_LONG).show();
                                }

                                fab.animate().scaleY(1).setDuration(400);
                                fab.animate().scaleX(1).setDuration(400);
                                dialogBox.cancel();
                            }
                        })
                .setNeutralButton(isUpdate ? "Назад":"",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();

        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(nameEditText.getText().toString())){
                    Toast.makeText(ProfilesActivity.this, "Введите имя", Toast.LENGTH_SHORT).show();
                    nameEditText.setBackgroundResource(R.drawable.edit_text_error);
                    return;
                }

                if(isUpdate){
                    String note = noteEditText.getText().toString();
                    if(!(TextUtils.isEmpty(note))){
                        bufNote = note;
                    }
                    updateFriend(nameEditText.getText().toString(), bufNote, position);
                    Toast.makeText(getApplicationContext(), "Данные изменены", Toast.LENGTH_LONG).show();
                } else {
                    int indexing = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt(PREF_KEY_PROFILE_PROGRESS, 0);
                    indexing = indexing + 1;
                    Log.d("log",indexing+"");
                    addFriend(nameEditText.getText().toString(), noteEditText.getText().toString(), indexing);
                    Toast.makeText(getApplicationContext(), "Друг добавлен", Toast.LENGTH_LONG).show();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt(PREF_KEY_PROFILE_PROGRESS, indexing).apply();
                }

                fab.animate().scaleY(1).setDuration(400);
                fab.animate().scaleX(1).setDuration(400);
                alertDialog.dismiss();
            }
        });



        //return false;
    }

    private void deleteFriend(Friend friend, int position){
        friends.remove(position);
        friendsAppDatabase.getFriendDAO().deleteFriend(friend);
        friendAdapter.notifyDataSetChanged();
    }

    private void updateFriend(String name, String note, int position){
        Friend friend = friends.get(position);

        friend.setName(name);
        friend.setNote(note);

        friendsAppDatabase.getFriendDAO().updateFriend(friend);

        friends.set(position, friend);

        friendAdapter.notifyDataSetChanged();
    }

    private void addFriend(String name, String note, int indexing){
        long id = friendsAppDatabase.getFriendDAO().addFriend(new Friend(0, name, note, indexing));
        Friend friend = friendsAppDatabase.getFriendDAO().getFriend(id);

        if(friend != null){
            friends.add( friend);
            friendAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_for_profiles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}