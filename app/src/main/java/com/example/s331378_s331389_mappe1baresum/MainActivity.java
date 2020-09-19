package com.example.s331378_s331389_mappe1baresum;

import androidx.annotation.Nullable;
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

import java.io.Serializable;
import java.util.ArrayList;
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
        //getStatistikk();

        saveStatistikk();

        getAlleOppgaver();

        String [] alleOppgaver = getResources().getStringArray(R.array.matteoppgaver);
        alle_oppgaver = arrayToString(alleOppgaver);
        saveAlleOppgaver();

    }



    @Override
    protected void onResume() {
        lesPref();
        super.onResume();
    }

    public void btnStartSpill(View v) {
        //statistikk.add("KUKBAJS");
        //System.out.println(statistikk.get(0));
        Intent i = new Intent(this, SpillActivity.class);
        //i.putStringArrayListExtra("statistikk",statistikk);
        startActivityForResult(i,420);
    }

    public void btnPreferanser(View v) {
        Intent intent = new Intent(this, SetPreferencesActivity.class);
        startActivity(intent);
    }

    public void btnSeStatistikk(View v) {
        Intent intent = new Intent(this, StatistikkActivity.class);
        intent.putExtra("statistikk",statistikk);
        startActivityForResult(intent,69);
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

    public void saveStatistikk(){
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString("statistikk",statistikk).apply();

    }
    public void getStatistikk(){
        statistikk  = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString("statistikk","");

    }

    public void saveAlleOppgaver(){
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString("alle_oppgaver",alle_oppgaver).apply();

    }
    public void getAlleOppgaver(){
        alle_oppgaver  = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString("alle_oppgaver","");

    }

    public static String [] stringtoArray(String toArray){
        String [] array = toArray.split("\n");
        return array;
    }
    public static String arrayToString(String [] array){
        String ut="";
        for(String etSpill : array){
            ut+=etSpill+"\n";
        }
        return ut;
    }


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("NOCE");
        if(requestCode==420){
            if(resultCode==RESULT_CANCELED){
                statistikk = data.getStringArrayListExtra("statistikk");
            }if(resultCode==RESULT_OK){
                statistikk = data.getStringArrayListExtra("statistikk");
                for(String k : statistikk){
                    System.out.println(k);
                }
                Intent i = new Intent(this, SpillActivity.class);
                i.putStringArrayListExtra("statistikk",statistikk);
                startActivityForResult(i,420);
            }

        }if(requestCode==69){
            if(resultCode==RESULT_OK){
                statistikk = data.getStringArrayListExtra("statistikk");

            }
        }


    }*/
}