package com.example.s331378_s331389_mappe1baresum;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpillActivity extends AppCompatActivity {

    MatteSpill etSpill;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);
        Resources res = getResources();



    }

    public void btnEn(View v) {

    }
    public void btnTo(View v) {

    }
    public void btnTre(View v) {

    }
    public void btnFire(View v) {

    }
    public void btnFem(View v) {

    }
    public void btnSeks(View v) {

    }
    public void btnSju(View v) {

    }
    public void btnOtte(View v) {

    }
    public void btnNi(View v) {

    }
    public void btnNull(View v) {

    }
    public void btnFjern(View v) {

    }
    public void btnLever(View v) {

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
