package com.example.s331378_s331389_mappe1baresum;


import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.LocusId;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.PersistableBundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.prefs.PreferenceChangeListener;

public class SetPreferencesActivity extends PreferenceActivity{

    public static final String listSprok = "listSprok";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sp.getString(listSprok, "0");
        System.out.println(value);
        if (value.equals("1")) {
            norsk();
        } else {
            tysk();
        }
        super.onCreate(savedInstanceState);
        PrefsFragment prefsFragment = new PrefsFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, prefsFragment).commit();

    }//onCreate ends


    public static class PrefsFragment extends PreferenceFragment{
        private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            //Lytter etter endringer av valg i Preferencelistene
            preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                        getActivity().recreate();
                    }
                };

        }

        //registrer og uregistrerer endringene
       @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
            Log.d("TAG", "Er i onResume");
        }
       @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
            Log.d("TAG", "Er i onPause");
        }

    } //PrefsFragment end




    //Kode for endring av land
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


    } //Settland

    public void tysk() {
        settland("de");
        //recreate();
    }

    public void norsk() {
        settland("no");
        //recreate();
    } //endring av språk

} //SetPreferencesActivity End


