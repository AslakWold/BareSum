package com.example.s331378_s331389_mappe1baresum;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpillActivity extends AppCompatActivity {

    String [] oppgaverOgSvar;
    int antallOppgaver;
    int antRiktige;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        Resources res = getResources();
        oppgaverOgSvar = res.getStringArray(R.array.matteoppgaver);
        String [] utOppgaver = MatteSpill.giOppgaver(oppgaverOgSvar,antallOppgaver);
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
