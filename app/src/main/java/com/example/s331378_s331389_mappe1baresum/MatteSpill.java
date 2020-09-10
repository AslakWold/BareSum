package com.example.s331378_s331389_mappe1baresum;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MatteSpill {

    public static String[] giOppgaver(String [] oppgaver, int antOppg){

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
                utOppgaver[i] = oppgaver [nr];
            }else{
                i--;
            }

        }

        return utOppgaver;
    }

    public static void splittOppgaver(String [] oppgaverOgSvar, String [] oppgaver, String [] svar){
        oppgaver = new String[oppgaverOgSvar.length];
        svar = new String[oppgaverOgSvar.length];

        for(int i = 0; i < oppgaverOgSvar.length; i++){
            String [] split = oppgaverOgSvar[i].split("=",2);
            oppgaver[i]=split[0];
            svar[i]=split[1];
        }
    }
}
