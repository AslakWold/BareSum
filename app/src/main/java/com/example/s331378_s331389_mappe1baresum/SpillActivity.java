package com.example.s331378_s331389_mappe1baresum;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpillActivity extends AppCompatActivity {

    MatteSpill etSpill;
    TextView txtOppgaver;
    TextView txtSvar;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);
        Resources res = getResources();

        txtOppgaver = findViewById(R.id.txtOppgaver);
        txtSvar = findViewById(R.id.txtSvar);
    }

    public void btnEn(View v) {
        skrivInn(1);
    }
    public void btnTo(View v) {
        skrivInn(2);
    }
    public void btnTre(View v) {
        skrivInn(3);
    }
    public void btnFire(View v) {
        skrivInn(4);
    }
    public void btnFem(View v) {
        skrivInn(5);
    }
    public void btnSeks(View v) {
        skrivInn(6);
    }
    public void btnSju(View v) {
        skrivInn(7);
    }
    public void btnOtte(View v) {
        skrivInn(8);
    }
    public void btnNi(View v) {
        skrivInn(9);
    }
    public void btnNull(View v) {
        skrivInn(0);
    }


    public void btnFjern(View v) {
        String svar = (String)txtSvar.getText();
        String nyttSvar = "";
        char [] cSvar = svar.toCharArray();
        for(int i = 0;i < cSvar.length-2;i++){
            nyttSvar+=cSvar[i];
        }


    }
    public void btnLever(View v) {

    }

    public void skrivInn(int tall){
        String svar = (String)txtSvar.getText();
        svar+=tall;
        txtSvar.setText(svar);
    }





    public void leggInnVerdi(TextView tv, int verdi){
        String gmlVerdi = (String)tv.getText();
        
        if(gmlVerdi!=null) {
            tv.setText(gmlVerdi + verdi);
        }else{
            tv.setText(verdi);
        }
    }


    //metode som fjerner siste tall i svarboksen

    /*
    //Må legge inn textview og knapp i "guiet".
    public void btnfjern(View v){

        TextView svar = (TextView)findViewById(R.id.txtSvar);

        String gmlSvar = txtSvar.getText();
        char [] nyttSvarArray = gmlSvar.toCharArray();
        String nyttSvar = "";
        for(int i = 0; i < nyttSvarArray.length - 1; i++){
            nyttSvar+=nyttSvarArray[i];
        }

        txtSvar.setText(nyttSvar);


    }*/


    /*public void btnLeverOppgave(View v){

    }*/

}
