package com.example.s331378_s331389_mappe1baresum;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String listSprok = "listSprok";
    public String statistikk;
    public String ID;
    public String alle_oppgaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lesPref();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

        getAlleOppgaver();

        //Henter oppgaver
        String [] alleOppgaver = getResources().getStringArray(R.array.matteoppgaver);
        alle_oppgaver = arrayToString(alleOppgaver);
        saveAlleOppgaver();

    }


    //Bevarer tilstanden i MainActivity ved rotasjon
    @Override
    protected void onResume() {
        lesPref();
        super.onResume();
    }

    //Buttons ---->

    public void btnStartSpill(View v) {
        Intent i = new Intent(this, SpillActivity.class);
        startActivityForResult(i,420);
    }

    public void btnPreferanser(View v) {
        Intent intent = new Intent(this, SetPreferencesActivity.class);
        startActivity(intent);
    }

    public void btnSeStatistikk(View v) {
        Intent intent = new Intent(this, StatistikkActivity.class);
        startActivityForResult(intent,69);
    } //Buttons - slutt



    //Metode for å lagre ARRAY i SHAREDPREFERENCES
    public static String arrayToString(String [] array){
        String ut="";
        for(String etSpill : array){
            ut+=etSpill+"\n";
        }
        return ut;
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
    }

    public void norsk() {
        settland("no");
    } //endring av språk

    public void lesPref(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sp.getString(listSprok, "0");

        if(value.equals("1")){
            norsk();
        }else{
            tysk();
        }
    }


    //METODER FOR HENTING FRA TIL SHAREDPREFERENCES

    public void saveStatistikk() {
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putString("statistikk", statistikk)
                .apply();

    }

    public void saveAlleOppgaver(){
        getSharedPreferences("PREFERENCE",MODE_PRIVATE)
                .edit()
                .putString("alle_oppgaver",alle_oppgaver)
                .apply();

    }

    public void getAlleOppgaver(){
        alle_oppgaver  = getSharedPreferences("PREFERENCE",MODE_PRIVATE)
                .getString("alle_oppgaver","");

    }

}//Mainactivity end