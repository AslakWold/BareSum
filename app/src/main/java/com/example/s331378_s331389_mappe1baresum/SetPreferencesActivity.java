package com.example.s331378_s331389_mappe1baresum;


import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import java.util.Locale;

public class SetPreferencesActivity extends PreferenceActivity{

    public static final String listSprok = "listSprok";


    @Override
    protected void onCreate(Bundle savedInstanceState){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

        String value = sp.getString(listSprok, "0");
        System.out.println(value);
        if(value.equals("1")){
            settland("no");
        }else{
            settland("de");
        }
        super.onCreate(savedInstanceState);
        PrefsFragment prefsFragment = new PrefsFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, prefsFragment).commit();
    }


    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        Locale ny = new Locale((landskode));
        Locale curr = getResources().getConfiguration().locale;

        if(!curr.equals(ny)){
            cf.setLocale(ny);
            res.updateConfiguration(cf, dm);
            recreate();
        }
    }




    public static class PrefsFragment extends PreferenceFragment{

        private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                        getActivity().recreate();
                    }
            };
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        }
        }
    }