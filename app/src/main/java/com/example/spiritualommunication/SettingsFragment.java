package com.example.spiritualommunication;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import static android.content.Context.MODE_PRIVATE;
import static com.example.spiritualommunication.MainActivity.THEMES_PROGRESS;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    Preference progressPreference;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preferences);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount(); // Получаем количество настроек на экране

        for (int i = 0; i < count; i++){ // пробегаем по списку всех настроек
            Preference preference = preferenceScreen.getPreference(i); // Получаем имя i-той настройки
            String value = sharedPreferences.getString(preference.getKey(),""); // Получаем значение 14 18 20 24 28 установленное в i-ой настройке, preference.getKey() - Получаем имя(ключ) нашей настройки
            //Log.d("preference","" + preference);
            //Log.d("value","" + value);
            setPreferenceLabel(preference,value);
        }

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
                        sharedPreferences = getContext().getSharedPreferences(THEMES_PROGRESS,MODE_PRIVATE);
                        sharedPreferences.edit().clear().commit();
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
        String value = sharedPreferences.getString(preference.getKey(),""); // Получаем значение 14 18 20 24 28 установленное в i-ой настройке, preference.getKey() - Получаем имя(ключ) нашей настройки

        setPreferenceLabel(preference, value);
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
