package com.example.s331378_s331389_mappe1baresum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StatistikkActivity extends AppCompatActivity {

    TextView txtSpillList;
    EditText ID;
    String statistikk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}
