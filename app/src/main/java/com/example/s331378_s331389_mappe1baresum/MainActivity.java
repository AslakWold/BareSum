package com.example.s331378_s331389_mappe1baresum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String listSprok = "listSprok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lesPref();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);


    }



    @Override
    protected void onResume() {
        lesPref();
        super.onResume();
    }

    public void btnStartSpill(View v) {
        Intent i = new Intent(this, SpillActivity.class);
        startActivity(i);
    }

    public void btnPreferanser(View v) {
        Intent intent = new Intent(this, SetPreferencesActivity.class);
        startActivity(intent);
    }

    public void btnSeStatistikk(View v) {

    }

    //kode for endring av språk

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

    public void tysk() {
        settland("de");
        //recreate();
    }

    public void norsk() {
        settland("no");
        //recreate();
    } //endring av språk

    public void lesPref(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sp.getString(listSprok, "0");
        System.out.println(value);
        if(value.equals("1")){
            norsk();
        }else{
            tysk();
        }
    }
}