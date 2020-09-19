package com.example.s331378_s331389_mappe1baresum;

public class MatteSpill {

    int antall_oppgaver;
    int antall_riktige;
    String [] svar;
    String [] oppgaver;
    String [] oppgaverOgSvar;

    //Konstruktører
    public MatteSpill(){

    }

    //konstruktør som setter alle verdier ved hjelp av metodene .

    public MatteSpill(int antall_oppgaver, String [] alle_oppgaver){
        this.antall_oppgaver = antall_oppgaver;
        oppgaverOgSvar=giOppgaver(antall_oppgaver,alle_oppgaver);
        svar = extractSvar(oppgaverOgSvar);
        oppgaver = extractOppgaver(oppgaverOgSvar);

        antall_riktige = 0;
    }


    //GET og SET metoder

    public void setAntall_riktige(int antall_riktige) {
        this.antall_riktige = antall_riktige;
    }

    public int getAntall_riktige() {
        return antall_riktige;
    }

    public String[] getSvar() {
        return svar;
    }

    public String[] getOppgaver() {
        return oppgaver;
    }

    public String[] getOppgaverOgSvar() {
        return oppgaverOgSvar;
    }


    //Metode som gir ut oppgaver til et startet spill
        //gir ut et visst antall oppgaver fra et tilgjengelig antall oppgaver

    public static String[] giOppgaver(int antOppg, String [] alle_oppgaver){
        int nr;
        String [] utOppgaver = new String[antOppg];
        int [] brukteOppg = new int[antOppg];
        boolean exists;  //verdi som sjekker at man ikke får samme oppgave 2 ganger.

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



    //metode som gir ut kun oppgavene fra regnestykkene til spillet
    public String [] extractOppgaver(String [] oppgaverOgSvar){
        String [] oppgaver = new String[oppgaverOgSvar.length];

        for(int i = 0; i < oppgaverOgSvar.length; i++){
            String [] split = oppgaverOgSvar[i].split("=",2);
            oppgaver[i] = split[0];
        }

        return oppgaver;
    }


    //Metode som gir ut kun svar fra regnestykkene til spillet
    public String [] extractSvar(String [] oppgaverOgSvar){
        String [] svar = new String[oppgaverOgSvar.length];

        for(int i = 0; i < oppgaverOgSvar.length; i++){
            String [] split = oppgaverOgSvar[i].split("=",2);
            svar[i]=split[1];
        }

        return svar;
    }


} //Mattespill ends
