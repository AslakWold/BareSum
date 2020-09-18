package com.example.s331378_s331389_mappe1baresum;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StatistikkActivity extends AppCompatActivity {

    TextView txtSpillList;
    EditText ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistikk);

        txtSpillList = findViewById(R.id.txtSpillStat);
        ID = findViewById((R.id.etID));

        Intent i = getIntent();
        ArrayList<String> oppgaverStatistikk = i.getStringArrayListExtra("statistikk");

        String id = getResources().getString(R.string.id);
        String dato = getResources().getString(R.string.dato);
        String riktige = getResources().getString(R.string.riktige);
        String feil = getResources().getString(R.string.antfeil);
        String totalt = getResources().getString(R.string.totalt);

        String spillList =  id + "  " + dato+ "   "+riktige+"   "+feil+"    "+totalt+"\n";

        for(String etSpill : oppgaverStatistikk){
            spillList += etSpill + "\n";
        }

        txtSpillList.setText(spillList);

    }
}
