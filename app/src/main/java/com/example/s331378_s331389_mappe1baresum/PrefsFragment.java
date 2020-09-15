package com.example.s331378_s331389_mappe1baresum;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;
import java.util.prefs.Preferences;

public class PrefsFragment extends PreferenceFragment {

    public static final String listSprok = "valgt_språk";
    public static final String antallOppgaver = "antall_oppgaver";
    private SharedPreferences.OnSharedPreferenceChangeListener prefSWListener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        prefSWListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

                if(s.equals(listSprok)){
                    //lesPref();
                }
                if(s.equals(antallOppgaver)){
                    Preference sprokPref = findPreference(s);
                    sprokPref.setSummary(sharedPreferences.getString(s, ""));
                }
            }
        };
    }
    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        Locale ny = new Locale((landskode));
        cf.setLocale(ny);
        res.updateConfiguration(cf, dm);
    }



    @Override
    public void onResume(){
        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(prefSWListener);
    }

    @Override
    public void onPause() {
        super.onPause();

        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(prefSWListener);
    }
}
