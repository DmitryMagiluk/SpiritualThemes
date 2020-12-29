package com.example.spiritualommunication;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
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
