package com.example.s331378_s331389_mappe1baresum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class SpillActivity extends AppCompatActivity implements MyDialog.DialogClickListener,NyttSpillDialog.DialogListener{

    public static final String LIST_ALT_OPPGAVER = "listAntallOppgaver";
    MatteSpill etSpill;
    TextView txtOppgaver;
    TextView txtSvar;
    int antall_oppgaver;
    int first;
    int teller;
    String [] alle_oppgaver;
    String [] oppgaver;
    String [] svar;
    ArrayList<String> oppgaverStatistikk;
    Toolbar spillToolbar;


    protected void onCreate(Bundle savedInstanceState){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sp.getString(LIST_ALT_OPPGAVER, "0");
        System.out.println(value);
        if(value.equals("1")){
            antall_oppgaver = 5;
        }else if(value.equals("2")){
            antall_oppgaver = 10;
        }else{
            antall_oppgaver = 25;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);
        Resources res = getResources();
        txtOppgaver = findViewById(R.id.txtOppgaver);
        txtSvar = findViewById(R.id.txtSvar);

        alle_oppgaver=getResources().getStringArray(R.array.matteoppgaver);
        etSpill = new MatteSpill(antall_oppgaver,alle_oppgaver);

        oppgaver = etSpill.getOppgaver();
        svar = etSpill.getSvar();

        teller = 0;
        first = 0;

        txtOppgaver.setText(oppgaver[teller]);

        //Kode for tilbakeknapp
        spillToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(spillToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }


    @Override   //tilbakeknapp i spillvinduet
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
        for(int i = 0;i < cSvar.length-1;i++){
            nyttSvar+=cSvar[i];
        }
        txtSvar.setText(nyttSvar);
    }


    public void btnLever(View v) {
        String riktig_svar = (String)txtSvar.getText();
        String input_svar = svar[teller];
        if(teller+1 >= antall_oppgaver){
            dialogFerdig();
        }else{
            if(riktig_svar.equals(input_svar)){
                etSpill.setAntall_riktige(etSpill.getAntall_riktige()+1);
                txtSvar.setText(this.getString(R.string.riktig));

            }else{
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
        finish();
    }

public void  ferdigSpill(int RESULT){

        Intent i = getIntent();
        ArrayList<String> statistikk = i.getStringArrayListExtra("statistikk");
        String a = ("12/12/12:"+etSpill.getAntall_riktige()+"/"+antall_oppgaver);
        statistikk.add(a);

        for(String w : statistikk){
            System.out.println(w);
        }

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("statistikk",statistikk);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT);
        finish();


    }

    @Override
    public void btnStartNytt() {
        ferdigSpill(RESULT_OK);
    }

    @Override
    public void btnTilbake() {
        ferdigSpill(RESULT_CANCELED);
    }
}
