package com.example.s331378_s331389_mappe1baresum;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MatteSpill {

    int antall_oppgaver;
    int antallRiktige;
    String [] svar;
    String [] oppgaver;

    public MatteSpill(int antall_oppgaver, String [] alle_oppgaver){
        this.antall_oppgaver = antall_oppgaver;
        String [] oppgaverOgSvar=giOppgaver(antall_oppgaver,alle_oppgaver);
        svar = getSvar(oppgaverOgSvar);
        oppgaver = getOppgaver(oppgaverOgSvar);

        antallRiktige = 0;
    }

    public static String[] giOppgaver(int antOppg, String [] alle_oppgaver){

        int nr;
        String [] utOppgaver = new String[antOppg];
        int [] brukteOppg = new int[antOppg];
        boolean exists = false;



        for(int i = 0; i < antOppg; i++){
            exists = false;
            nr = (int)(Math.random() * (25));
            brukteOppg[i] = nr;

            for(int j = 0; j <= i; j++){
                if(nr == brukteOppg[j] && i!=j){
                    exists = true;
                }
            }

            if(!exists){
                utOppgaver[i] = alle_oppgaver[nr];
            }else{
                i--;
            }

        }

        return utOppgaver;
    }

    public static String [] getOppgaver(String [] oppgaverOgSvar){
        String [] oppgaver = new String[oppgaverOgSvar.length];

        for(int i = 0; i < oppgaverOgSvar.length; i++){
            String [] split = oppgaverOgSvar[i].split("=",2);
            oppgaver[i] = split[0];
        }

        return oppgaver;
    }

    public static String [] getSvar(String [] oppgaverOgSvar){
        String [] svar = new String[oppgaverOgSvar.length];

        for(int i = 0; i < oppgaverOgSvar.length; i++){
            String [] split = oppgaverOgSvar[i].split("=",2);
            svar[i]=split[1];
        }

        return svar;
    }
}
