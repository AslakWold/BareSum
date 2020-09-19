package com.example.s331378_s331389_mappe1baresum;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Date;

public class SpillActivity extends AppCompatActivity implements MyDialog.DialogClickListener,NyttSpillDialog.DialogListener{

    public static final String LIST_ALT_OPPGAVER = "listAntallOppgaver";
    MatteSpill etSpill;

    TextView txtOppgaver;
    TextView txtSvar;
    TextView antallRiktigeSvar;
    TextView antallFeilSvar;

    int tellerRiktigeSvar = 0;
    int tellerFeilSvar = 0;

    int antall_oppgaver;
    int first;
    int teller;

    String [] alle_oppgaver;
    String [] oppgaver;
    String [] svar;
    ArrayList<String> oppgaverStatistikk;
    Toolbar spillToolbar;
    String statistikk;
    public int ID;


    protected void onCreate(Bundle savedInstanceState) {


        String besvarelse = "";
        String antallRiktigeSvarString = "";
        String antallFeilSvarString = "";
        etSpill = new MatteSpill();

        if (savedInstanceState != null) {
            int riktige;
            teller = savedInstanceState.getInt("teller");
            antall_oppgaver = savedInstanceState.getInt("antall_oppgaver");
            riktige = savedInstanceState.getInt("antall_riktige");
            oppgaver = savedInstanceState.getStringArray("oppgaver");
            svar = savedInstanceState.getStringArray("svar");
            antallRiktigeSvarString = savedInstanceState.getString("antallRiktigeSvar");
            antallFeilSvarString = savedInstanceState.getString("antallFeilSvar");


            besvarelse = savedInstanceState.getString("besvarelse");
            etSpill.setAntall_riktige(riktige);
        } else {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            String value = sp.getString(LIST_ALT_OPPGAVER, "0");
            System.out.println(value);
            if (value.equals("1")) {
                antall_oppgaver = 5;
            } else if (value.equals("2")) {
                antall_oppgaver = 10;
            } else {
                antall_oppgaver = 25;
            }

            String statistikk = "";
            getSharedPreferences("Preference", MODE_PRIVATE).edit().putString("statistikk", statistikk).apply();

            alle_oppgaver = getResources().getStringArray(R.array.matteoppgaver);
            etSpill = new MatteSpill(antall_oppgaver, alle_oppgaver);

            oppgaver = etSpill.getOppgaver();
            svar = etSpill.getSvar();

            teller = 0;
            first = 0;
            tellerRiktigeSvar = 0;
            tellerFeilSvar = 0;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);
        txtOppgaver = findViewById(R.id.txtOppgaver);
        txtSvar = findViewById(R.id.txtSvar);
        txtSvar.setText(besvarelse);
        txtOppgaver.setText(oppgaver[teller]);

        antallRiktigeSvar = findViewById(R.id.antallRiktigeSvar);
        antallFeilSvar = findViewById(R.id.antallFeilSvar);
        antallRiktigeSvar.setText(Integer.toString(tellerRiktigeSvar));
        antallFeilSvar.setText(Integer.toString(tellerFeilSvar));
        antallRiktigeSvar.setText(antallRiktigeSvarString);
        antallFeilSvar.setText(antallFeilSvarString);

        /*Kode for tilbakeknapp
        spillToolbar = (Toolbar) findViewById(R.id.toolbar);
        spillToolbar.setTitle("");
        setSupportActionBar(spillToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
    }

    public void startNyttSpill(){
        etSpill = new MatteSpill(antall_oppgaver,alle_oppgaver);
        teller = 0;
        first = 0;
        tellerFeilSvar = 0;
        tellerRiktigeSvar = 0;
        oppgaver = etSpill.getOppgaver();
        svar = etSpill.getSvar();
        txtSvar.setText("");
        txtOppgaver.setText(oppgaver[teller]);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("teller", teller);
        savedInstanceState.putInt("antall_oppgaver",antall_oppgaver);
        savedInstanceState.putInt("antall_riktige",etSpill.getAntall_riktige());
        savedInstanceState.putStringArray("oppgaver",oppgaver);
        savedInstanceState.putStringArray("svar",svar);

        String besvarelse = (String)txtSvar.getText()  ;
        savedInstanceState.putString("besvarelse", besvarelse);

        String antallRiktigeSvarString = (String) antallRiktigeSvar.getText();
        String antallFeilSvarString = (String) antallFeilSvar.getText();
        savedInstanceState.putString("antallRiktigeSvar", antallRiktigeSvarString);
        savedInstanceState.putString("antallFeilSvar", antallFeilSvarString);

    }


    /*
    @Override   //tilbakeknapp i spillvinduet
    public boolean onSupportNavigateUp() {
        //dialogAvslutt();
        onBackPressed();
        return true;
    } */




    public void onBackPressed() {
        dialogAvslutt();
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
        if(first==0){

        } else{
        String svar = (String)txtSvar.getText();
        String nyttSvar = "";
        char [] cSvar = svar.toCharArray();
        for(int i = 0;i < cSvar.length-1;i++){
            nyttSvar+=cSvar[i];
        }
        txtSvar.setText(nyttSvar);    }
    }


    public void btnLever(View v) {
        String riktig_svar = (String)txtSvar.getText();
        String input_svar = svar[teller];

        if(teller+1 >= antall_oppgaver){
            if(riktig_svar.equals(input_svar)) {
                tellerRiktigeSvar++;
                antallRiktigeSvar.setText(Integer.toString(tellerRiktigeSvar));
                dialogFerdig();
            } else {
                tellerFeilSvar++;
                antallFeilSvar.setText(Integer.toString(tellerFeilSvar));
                dialogFerdig();
            }
        }else {

            if (riktig_svar.equals(input_svar)) {
                tellerRiktigeSvar++;
                antallRiktigeSvar.setText(Integer.toString(tellerRiktigeSvar));
                etSpill.setAntall_riktige(etSpill.getAntall_riktige() + 1);
                txtSvar.setText(this.getString(R.string.riktig));
            } else {
                tellerFeilSvar++;
                antallFeilSvar.setText(Integer.toString(tellerFeilSvar));
                txtSvar.setText(this.getString(R.string.feil) + svar[teller]);

            }
            teller++;
            txtOppgaver.setText(oppgaver[teller]);
            first = 0;
        }
    }

    public void skrivInn(int tall){

        if(first != 0){
            String svar = (String)txtSvar.getText();
            svar+=tall;
            txtSvar.setText(svar);
        }else{
            String svar = tall + "";
            txtSvar.setText(svar);
            first = 1;
        }

    }

    //DIALOG

    public void dialogAvslutt(){
        DialogFragment dialogFragment = new MyDialog();
        dialogFragment.show(getSupportFragmentManager(),"avslutt");
    }

    public void dialogFerdig(){
        DialogFragment dialogFragment = new NyttSpillDialog();
        dialogFragment.show(getSupportFragmentManager(),"nytt");
    }

    @Override
    public void btnAvbryt() {
        return;
    }

    @Override
    public void btnAvslutt() {
        //onBackPressed();
        finish();
    }

public void  ferdigSpill(int RESULT){

        String a = "ID : antall riktige : antall feil : totalt + \n";
        getStatistikk("statistikk");
        getID("ID");

        int feil = antall_oppgaver - etSpill.getAntall_riktige();
        String nyttSpill = ID + " : " + etSpill.getAntall_riktige() + " : " + feil + " : " + antall_oppgaver + "\n";
        statistikk+=nyttSpill;
        ID+=1;
        saveStatistikk("statistikk");
        saveID("ID");
        if(RESULT==RESULT_OK){
            startNyttSpill();
        }else{
            finish();
        }
        System.out.println(statistikk);

    }

    @Override
    public void btnStartNytt() {
        ferdigSpill(RESULT_OK);
    }

    @Override
    public void btnTilbake() {
        ferdigSpill(RESULT_CANCELED);
    }

    // DIALOG

    public int genID(ArrayList<String>statistikk){
        int nyID;
        if(statistikk.size()==0){
            nyID = 1;
            return nyID;
        }
        String [] elementer = statistikk.get(statistikk.size()-1).split("\\s+");
        System.out.println(elementer[0] + "ELEMENTER");
        int nr = Integer.parseInt(elementer[0]);

        nyID = nr+1;

        return nyID;
    }

    public void saveStatistikk(String PREF){
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString(PREF,statistikk).apply();

    }
    public void getStatistikk(String PREF){
        statistikk  = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString(PREF,"");
    }


    public void saveID(String PREF){
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putInt(PREF,ID).apply();

    }
    public void getID(String PREF){
        ID  = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getInt(PREF,0);
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
