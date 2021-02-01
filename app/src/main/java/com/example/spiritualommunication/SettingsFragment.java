package com.example.spiritualommunication;


import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

import static android.content.Context.MODE_PRIVATE;
import static com.example.spiritualommunication.ProfilesActivity.PROFILE_THEMES_PROGRESS;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    Preference progressPreference, textSizePreference;
    int profileIdForSharedPrefSettings;


//    public SettingsFragment(Preference progressPreference) {
//        this.progressPreference = progressPreference;
//        //this.profileIdForSharedPref = profileIdForSharedPref;
//    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preferences);

        //profileIdForSharedPrefSettings = settingsActivity.getProfileIdForSharedPref();


        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount(); // Получаем количество настроек на экране

        for (int i = 0; i < count-1; i++){ // пробегаем по списку всех настроек
            Preference preference = preferenceScreen.getPreference(i); // Получаем имя i-той настройки
            String key = preference.getKey();
           //Log.d("preference","" + preference);
            //Log.d("value","" + value);
            if (preference.getKey().equals("text_size")){
                String value = sharedPreferences.getString(key,""); // Получаем значение 14 18 20 24 28 установленное в i-ой настройке, preference.getKey() - Получаем имя(ключ) нашей настройки (как пример)
                setPreferenceLabel(preference,value);
            }  else if (key.equals("app_color_theme")){
                String value = sharedPreferences.getString(key,"");
                setPreferenceLabel(preference, value);
            } else if (key.equals("switch_preference_night_mode")){

            }

        }

        //textSizePreference = findPreference("text_size");

        progressPreference = findPreference("progress_reset");

        progressPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
                View view1 = layoutInflaterAndroid.inflate(R.layout.layout_question, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
                alertDialogBuilderUserInput.setView(view1);

                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Да" , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                            }
                        })
                        .setNegativeButton("Нет",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {

                                    }
                                });
                final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Прогресс сброшен", Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreferences;
                        profileIdForSharedPrefSettings = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt("for_refresh_add_progress_index",-1);
                        Log.d("getProfi",profileIdForSharedPrefSettings+"");
                        sharedPreferences = getContext().getSharedPreferences("PROFILE_THEMES_PROGRESS" + profileIdForSharedPrefSettings, MODE_PRIVATE); //Для удаления прогресса в профиле
                        sharedPreferences.edit().clear().apply();
                        sharedPreferences = getContext().getSharedPreferences("PROFILE_THEMES_PROGRESS_" + profileIdForSharedPrefSettings, MODE_PRIVATE); //Для удаления заметок в профиле
                        sharedPreferences.edit().clear().apply();
                        alertDialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    private void setPreferenceLabel (Preference preference, String value){ // устанавливаем имя выбранной настройки в ListPreference под названием ListPreference
        if(preference instanceof ListPreference){ // Если полученная i-я настройка типа ListPreference то
            ListPreference listPreference = (ListPreference) preference; //Создаем обьект типа ListPreference и присваиваем ему имя i-той настройки
            int index = listPreference.findIndexOfValue(value); // получаем индекс установленного значения 14 18 20 24 28
            //Log.d("index","" + index);

            if(index >= 0 ){ // проверяем индекс на отрицательность
                listPreference.setSummary(listPreference.getEntries()[index]); // устанавливаем по индексу summary нашего ListPreference
                // Entries это массив названий (от ОЧЕНЬ МЕЛКИЙ до ОЧЕНЬ БОЛЬШОЙ)
            }
        }

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) { // слушатель изменения настройки в ListPreference
        Preference preference = findPreference(s); // находим настройку (название и то что в ней выбрано) по ключу s
        //Log.d("preference","" + preference); // выводится название настройки и то что в ней выбрано
        String key = preference.getKey();

        if (preference instanceof ListPreference){
            String value = sharedPreferences.getString(key,""); // Получаем значение 14 18 20 24 28 установленное в i-ой настройке, preference.getKey() - Получаем имя(ключ) нашей настройки
            setPreferenceLabel(preference, value);
            //Log.d("log",key +"");
            if (key.equals("app_color_theme")){
                TaskStackBuilder.create(getActivity())
                        .addNextIntent(new Intent(getActivity(), ProfilesActivity.class))
                        .addNextIntent(getActivity().getIntent())
                        .startActivities();
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { // переопределяем метод для того чтобы зарегестрировать слушатель изменений на PreferenceScreen
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
