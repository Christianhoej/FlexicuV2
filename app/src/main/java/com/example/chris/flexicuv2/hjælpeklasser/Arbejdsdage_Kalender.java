package com.example.chris.flexicuv2.hjælpeklasser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author Janus
 * Klassen skal bruges som et opslag til antallet af arbejdsdage i en periode mellem to datoer
 */
public class Arbejdsdage_Kalender {
    /**
     * Metoden finder antallet af arbejdsdage mellem to datoer.
     * @param dStart startdatoen format: "dd/MM/yyyy"
     * @param dSlut slutdatoen format : "dd/MM/yyyy"
     * @return -1 hvis slutdato kommer før startdato og ellers antallet af arbejdsdage (weekender og helligdage er ekskluderet) eller hvis den får et datoformat der ikke kan bruges
     */
    public static int findArbejdsdage(String dStart, String dSlut) {
        //
        int antalDageFraStart = checkDateIsOK(dStart, dSlut);

        if (antalDageFraStart >= 0) {


            String[] startDato = dStart.split("/");
            int startDag = Integer.parseInt(startDato[0].trim());
            int startMåned = Integer.parseInt(startDato[1].trim());
            int startÅr = Integer.parseInt(startDato[2].trim());


            String[] slutDato = dSlut.split("/");
            int slutDag = Integer.parseInt(slutDato[0].trim());
            int slutMåned = Integer.parseInt(slutDato[1].trim());
            int slutÅr = Integer.parseInt(slutDato[2].trim());

            ArrayList<ArbejdsDage> år = new ArrayList<>();
            int slut = Integer.parseInt(slutDato[2].trim());
            for (int start = Integer.parseInt(startDato[2].trim()); start <= slut; start++) { //Kører antal forskel på år
                ArbejdsDage a = new ArbejdsDage(start);
                år.add(a);

            }
            //Lægger alle dage fra alle år aftalen strækker sig over i "alleDage"
            ArrayList<Integer> alleDage = new ArrayList<>();
            for (ArbejdsDage at : år) {
                //find antal dage
                for (Integer i : at.getMuligearbejdsdageIÅret()) {
                    alleDage.add(i);
                }
            }
            //finder startdato
            int st = Arbejdsdage_Kalender.ArbejdsDage.getDayNumber(startDag, startMåned, startÅr) - 1; // -1 for at finde startplads i arrayet
            int total = st + antalDageFraStart;
            int antalArbDage = 0;
            for (int i = 0; i <= total; i++) {
                antalArbDage += alleDage.get(i);
            }
            int førStartDatoDage = 0;
            for (int i = 0; i < st; i++) {
                førStartDatoDage += alleDage.get(i);
            }
            return antalArbDage - førStartDatoDage;


        }

        return -1;
    }

    /**
     * Metoden udregner forskellen mellem
     *
     * @param dStart String for datostarten på form "dd/MM/yyy"
     * @param dSlut  String for slutdato på form "dd/MM/yyyy"
     * @return antallet af dage mellem de to datoer - returnerer -1 hvis datoformatet ikke kan parses
     */
    public static int checkDateIsOK(String dStart, String dSlut) {
        dSlut.replace(" ", "");
        dStart.replace(" ", "");
        String[] start = dStart.split("/");
        String[] slut = dSlut.split("/");



        Calendar cal1 = new GregorianCalendar(Integer.parseInt(start[2].trim()), (Integer.parseInt(start[1].trim())-1), Integer.parseInt(start[0].trim()));
        Date startDate = cal1.getTime();
        Calendar cal2 = new GregorianCalendar(Integer.parseInt(slut[2].trim()), (Integer.parseInt(slut[1].trim())-1), Integer.parseInt(slut[0].trim()));

        Date slutDate = cal2.getTime();
        int diffInDays = (int)( (slutDate.getTime() - startDate.getTime())
                / (1000 * 60 * 60 * 24) );
        //System.out.println(diffInDays + "diffindays");

        return diffInDays;
        }



    public static class ArbejdsDage {


        private int DetteÅr, startendeÅr;

        private int antalDage;
        private int nytårsdag;
        private int skærtorsdag;
        private int langfredag;
        private int påskedag;
        private int andenPåskedag;
        private int storeBededag;
        private int kristiHimmelfartsdag;
        private int pinsedag;
        private int førsteJuledag;
        private int andenJuledag;

        int returVærdi;

        private ArrayList<Integer> muligearbejdsdageIÅret = new ArrayList<Integer>();
        private ArrayList<Integer> helligDage;

        /**
         * Metode tjekker om det er afsluttende år
         *
         * @param afsluttendeÅr det år lejen afsluttes
         * @return true hvis det er det afsluttende år
         */
        public boolean isAfsluttendeÅr(int afsluttendeÅr) {
            return DetteÅr == afsluttendeÅr;
        }

        public boolean isStartendeÅr(int startendeÅr) {
            return startendeÅr == this.startendeÅr;
        }

        public ArbejdsDage(int år) {
            this.DetteÅr = år;
            returVærdi = 0;
            nytårsdag = 1;

            //udregn påskedag
            påskedag = getDayNumber(udregnPåskedag(2019, false), udregnPåskedag(2019, true), år);
            skærtorsdag = påskedag - 3;

            langfredag = skærtorsdag + 1;
            andenPåskedag = påskedag + 1;
            storeBededag = påskedag + 3 * 7 + 5;
            kristiHimmelfartsdag = påskedag + 5 * 7 + 4;
            pinsedag = påskedag + 7 * 7;

            if (!isSkudår(år)) {
                førsteJuledag = 365 - 6;
                andenJuledag = 365 - 5;
                antalDage = 365;
            } else {
                førsteJuledag = 366 - 6;
                andenJuledag = 366 - 5;
                antalDage = 366;
            }
            helligDage = new ArrayList<>();
            helligDage.add(nytårsdag);
            helligDage.add(skærtorsdag);
            helligDage.add(langfredag);
            helligDage.add(andenPåskedag);
            helligDage.add(storeBededag);
            helligDage.add(kristiHimmelfartsdag);
            helligDage.add(førsteJuledag);
            helligDage.add(andenJuledag);


            //Fjerner lørdage og søndage (der ikke allerede ligger på en søndag) fra muligearbejdsdage
            Calendar c = Calendar.getInstance();
            int dag = 1;
            while (dag <= antalDage) {
                String s = dag + "/01/" + år;
                try {
                    c.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Herunder sættes 0 hvis der ikke kan arbejdes og 1 hvis der kan
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                if (dayOfWeek != 6) {
                    if (dayOfWeek != 0)
                        muligearbejdsdageIÅret.add(1); //tilføj 1 på alle pladser der kan arbejdes
                    else muligearbejdsdageIÅret.add(0);
                } else muligearbejdsdageIÅret.add(0);
                dag++;
            }

            //gennemløber arraylist og sætter værdier pladsen der har en helligdag = 0 fordi der ikek kan arbejdes
            for (Integer i : helligDage) {

                muligearbejdsdageIÅret.set((i - 1), 0);
            }

            int count = 0;
            for (int i : muligearbejdsdageIÅret) {
                count = count + i;
            }
        }

        /**
         * Metoden tjekker om der er skudår
         *
         * @param år Årstal
         * @return sandt hvis det er skudår
         */
        public boolean isSkudår(int år) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, år);
            return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
        }


        /**
         * Metoden returnere dagsnr i det år der tastes ind.
         *
         * @param dag
         * @param måned
         * @param år
         * @return dagsNummer
         */
        public static int getDayNumber(int dag, int måned, int år) {
            måned = måned - 1;
            GregorianCalendar gc = new GregorianCalendar();
            gc.set(GregorianCalendar.DAY_OF_MONTH, dag);
            gc.set(GregorianCalendar.MONTH, måned);
            gc.set(GregorianCalendar.YEAR, år);
            int dagsNr = gc.get(GregorianCalendar.DAY_OF_YEAR);
            return dagsNr;
        }

        /**
         * Lånt fra: https://stackoverflow.com/questions/26022233/calculate-the-date-of-easter-sunday
         * metoden returnerer enten dag eller måned for påskedag, boolean måned angiver om man vil have måned eller dag
         *
         * @param år    året påskedag skal findes for
         * @param måned true hvis man ønsker måned, false hvis man ønsker dag
         * @return månedsnummer, hvis måned = true eller dagsnummer hvis måned = false
         */
        private int udregnPåskedag(int år, boolean måned) {
            int a = år % 19,
                    b = år / 100,
                    c = år % 100,
                    d = b / 4,
                    e = b % 4,
                    g = (8 * b + 13) / 25,
                    h = (19 * a + b - d - g + 15) % 30,
                    j = c / 4,
                    k = c % 4,
                    m = (a + 11 * h) / 319,
                    r = (2 * e + 2 * j - k - h + m + 32) % 7,
                    n = (h - m + r + 90) / 25,
                    p = (h - m + r + n + 19) % 32;

            if (måned)
                return n;
            else
                return p;
        }


        public ArrayList<Integer> getMuligearbejdsdageIÅret() {
            return muligearbejdsdageIÅret;
        }

    }
}









