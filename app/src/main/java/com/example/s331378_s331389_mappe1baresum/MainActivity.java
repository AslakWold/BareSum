package com.example.s331378_s331389_mappe1baresum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}