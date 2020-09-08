package com.example.s331378_s331389_mappe1baresum;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MatteSpill {
    String [] oppgaver = new String[25];

    public void fyllOppgaver(String [] oppgaver){
        oppgaver[0]="12 + 10 = 22";
        oppgaver[1]="2 + 10 = 12";
        oppgaver[2]="13 + 13 = 26";
        oppgaver[3]="5 + 9 = 14";
        oppgaver[4]="12 + 10 = 22";
        oppgaver[5]="12 + 10 = 22";
        oppgaver[6]="12 + 10 = 22";
        oppgaver[7]="12 + 10 = 22";
        oppgaver[8]="12 + 10 = 22";
        oppgaver[9]="12 + 10 = 22";
        oppgaver[10]="12 + 10 = 22";
        oppgaver[11]="12 + 10 = 22";
        oppgaver[12]="12 + 10 = 22";
        oppgaver[13]="12 + 10 = 22";
        oppgaver[14]="12 + 10 = 22";
        oppgaver[15]="12 + 10 = 22";
        oppgaver[16]="12 + 10 = 22";
        oppgaver[17]="12 + 10 = 22";
        oppgaver[18]="12 + 10 = 22";
        oppgaver[19]="12 + 10 = 22";
        oppgaver[20]="12 + 10 = 22";
        oppgaver[21]="12 + 10 = 22";
        oppgaver[22]="12 + 10 = 22";
        oppgaver[23]="12 + 10 = 22";
        oppgaver[24]="12 + 10 = 22";
    }

    public String[] giOppgaver(String [] oppgaver, String [] utOppgaver, int antOppg){

        int nr;
        ArrayList brukteOppg = new ArrayList<Integer>();

        for(int i = 0; i > antOppg; i++){
            nr = (int)(Math.random() * (25));
            for(int j = 0; j >= brukteOppg.size(); i++){

            }

            utOppgaver[i] = oppgaver [nr];
        }

        return utOppgaver;
    }
}
