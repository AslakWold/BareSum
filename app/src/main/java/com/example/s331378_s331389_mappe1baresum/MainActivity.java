package com.example.s331378_s331389_mappe1baresum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnStartSpill(View v) {
        Intent i = new Intent(this, SpillActivity.class);
        startActivity(i);
    }

    public void btnPreferanser(View v) {

    }

    public void btnSeStatistikk(View v) {

    }

    //kode for endring av språk

    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.setLocale(new Locale(landskode));
        res.updateConfiguration(cf, dm);
    }

    public void tysk(View v) {
        settland("de");
        recreate();
    }

    public void norsk(View v) {
        settland("no");
        recreate();
    } //endring av språk
}