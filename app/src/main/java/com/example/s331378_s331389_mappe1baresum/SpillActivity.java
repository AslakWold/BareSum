package com.example.s331378_s331389_mappe1baresum;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SpillActivity extends AppCompatActivity {

    String [] oppgaverOgSvar;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        Resources res = getResources();
        oppgaverOgSvar = res.getStringArray(R.array.matteoppgaver);
    }

}
