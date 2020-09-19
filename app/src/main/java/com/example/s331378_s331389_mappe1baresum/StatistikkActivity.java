package com.example.s331378_s331389_mappe1baresum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class StatistikkActivity extends AppCompatActivity {

    public static String listSprok = "listSprok";
    TextView txtSpillList;
    EditText ID;
    String statistikk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lesPref();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistikk);

        txtSpillList = findViewById(R.id.txtSpillStat);
        ID = findViewById((R.id.etID));


        String id = getResources().getString(R.string.id);
        String dato = getResources().getString(R.string.dato);
        String riktige = getResources().getString(R.string.riktige);
        String feil = getResources().getString(R.string.antfeil);
        String totalt = getResources().getString(R.string.totalt);

        String spillList =  id + "\t" + riktige + "\t" + feil + "\t" + totalt + "\n";

        getStatistikk("statistikk");

        txtSpillList.setText(spillList+statistikk);

    }

    public void btnSlettSpill(View view) {

        String id = ID.getText().toString();
        int idSlett = Integer.parseInt(id);

        getStatistikk("statistikk");
        ArrayList<String> arrayListStat = stringtoArray(statistikk);


        for(int i = 1; i< arrayListStat.size()+1; i++){
            String [] elementer = arrayListStat.get(i-1).split("\\s+");
            int idSjekk = Integer.parseInt(elementer[0]);

            if(idSlett==idSjekk){
                arrayListStat.remove(i-1);
                break;
            }
        }

        statistikk = arraylistToString(arrayListStat);

        txtSpillList.setText(statistikk);
        saveStatistikk("statistikk");

        //finish();
    }



    public void saveStatistikk(String PREF){
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString(PREF,statistikk).apply();

    }
    public void getStatistikk(String PREF){
        statistikk  = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString(PREF,"");
    }


    public static ArrayList<String> stringtoArray(String toArray){
        String [] array = toArray.split("\n");
        System.out.println(array.length);
        ArrayList<String> tmp = new ArrayList<>();

        for(int i = 0;i<array.length;i++){
            tmp.add(array[i]);
        }
        return tmp;
    }
    public static String arraylistToString(ArrayList<String> arrayList){
        String ut="";
        for(String etSpill : arrayList){
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
