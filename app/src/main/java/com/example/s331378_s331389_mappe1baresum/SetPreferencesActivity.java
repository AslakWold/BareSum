package com.example.s331378_s331389_mappe1baresum;


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


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.prefs.PreferenceChangeListener;

public class SetPreferencesActivity extends PreferenceActivity{

    public static final String listSprok = "listSprok";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
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

        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if(s.equals(listSprok)){
                    String value = sharedPreferences.getString(s,"");
                    System.out.println(value+" AJJAJAJJAJAJAJJAJ");
                    if(value.equals(1)){
                        settland("no");
                        recreate();
                    }
                    if(value.equals(2)) {
                        settland("de");
                        recreate();
                    }
                }


            }
        };
    }

    @Override
    protected void onResume() {
        System.out.println("RESUME");
        super.onResume();


    }


    @Override
    protected void onStop() {
        System.out.println("STOP");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("DESTROY");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("ONSAVEONSTANCESTATE");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        System.out.println("ONRESTOREINSTAANCESTATE");
        super.onRestoreInstanceState(state);
    }


    @Override
    public void onContentChanged() {
        System.out.println("onContentChanged");
        super.onContentChanged();

    }


    @Override
    public Intent onBuildStartFragmentIntent(String fragmentName, Bundle args, int titleRes, int shortTitleRes) {
        System.out.println("onBuildStartFragmentIntent");
        return super.onBuildStartFragmentIntent(fragmentName, args, titleRes, shortTitleRes);
    }

    @Override
    public void startWithFragment(String fragmentName, Bundle args, Fragment resultTo, int resultRequestCode) {
        System.out.println("startwithfragment1");
        super.startWithFragment(fragmentName, args, resultTo, resultRequestCode);
    }

    @Override
    public void startWithFragment(String fragmentName, Bundle args, Fragment resultTo, int resultRequestCode, int titleRes, int shortTitleRes) {
        System.out.println("startwithfragment2");
        super.startWithFragment(fragmentName, args, resultTo, resultRequestCode, titleRes, shortTitleRes);
    }


    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        System.out.println("onpreferencestartfragment");
        return super.onPreferenceStartFragment(caller, pref);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
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
    public void lesPref(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sp.getString(listSprok, "0");
        System.out.println(value);
        if(value.equals("1")){
            settland("no");
        }else{
            settland("de");
        }
    }



    public static class PrefsFragment extends PreferenceFragment{

        public static final String PREF_SPROK = "listSprok";
        public static final String PREF_ANTALL = "listAntallOppgaver";

        private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
        public static String landskode = "de";


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);



            preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                    Preference pref_sprok = findPreference(PREF_SPROK);
                    Preference pref_ant = findPreference(PREF_ANTALL);
                    if(s.equals(listSprok)){
                        String value = sharedPreferences.getString(s,"");
                        System.out.println(value+" AJJAJAJJAJAJAJJAJ");
                        if(value.equals("1")){
                            settland("no");
                            //pref_sprok.setTitle("VELG SPRÅK");
                            //System.out.println(pref_sprok.getTitle());
                            //pref_ant.setTitle("VELGANTALL OPPGAVER");
                        }
                        if(value.equals("2")) {
                            settland("de");
                            //pref_sprok.setTitle("WEHLE SPRACHEN");
                            //pref_ant.setTitle("WEHLE VIELE AUFGABEN");
                        }
                        getActivity().recreate();
                    }


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
        public void settland(String landskode) {
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration cf = res.getConfiguration();
            Locale ny = new Locale((landskode));
            Locale curr = getResources().getConfiguration().locale;

            if(!curr.equals(ny)){
                cf.setLocale(ny);
                res.updateConfiguration(cf, dm);
            }
        }
    }


}


    /*public static   class  PrefsFragment extends PreferenceFragment {

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }

    }
*/