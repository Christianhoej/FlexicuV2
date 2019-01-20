package com.example.chris.flexicuv2.startskærm.udlej;

import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;

class Udlejning_Presenter {
    private final String ERRORKRONOLOGISKDATO = "Den valgte slutdato falder før startdatoen";
    private UpdateUdlejning updateUdlejning;
    private final String ERRORMEDARBEJDER = "MEDARBEJDERFEJL";
    private final String ERRORSTARTDATO = "STARTDATOFEJL";
    private final String ERRORSLUTDATO = "SLUTDATOFEJL";
    private final String ERRORTIMEPRIS = "TIMEPRISFEJL";
    private final String ERRORINGENARBEJDSDAGE = "Den valgte periode har ingen arbejdsdage";

    public Udlejning_Presenter(UpdateUdlejning updateUdlejning){
        this.updateUdlejning = updateUdlejning;
    }

    /**
     * Metoden anvendes til at finde totale antal arbejdsdage i perioden
     * @param startdato
     * @param slutdato
     */
    public void udregnArbejdsdage(String startdato, String slutdato){
        startdato = startdato.replace(" ", "");
        slutdato = slutdato.replace(" ","");

        int arbDage = Arbejdsdage_Kalender.findArbejdsdage(startdato, slutdato);
        if(arbDage<0)
            arbDage = 0;
        updateUdlejning.opdaterAntalArbejdsdage(arbDage);

    }



    public boolean checkKorrektUdfyldtInformation(String startdato, String slutdato, int arbejdsdage, int timepris, String egetVærktøj, String kommentar) {

        int errors = 0;

        updateUdlejning.errorStartdato(null);
        updateUdlejning.errorSlutdato(null);
        updateUdlejning.errorMedarbejder(null);
        updateUdlejning.errorTimepris(null);
        updateUdlejning.errorArbejdsdage(null);

        boolean startdatoOK = startdato.equals(" dd / mm / yyyy ");
        if(startdatoOK){
            updateUdlejning.errorStartdato(ERRORSTARTDATO);
            errors++;
        }
        boolean slutdatoOK = !slutdato.equals(" dd / mm / yyyy ");
        if(!slutdatoOK){
            updateUdlejning.errorSlutdato(ERRORSLUTDATO);
            errors++;


        }
        if (errors ==0) {
            boolean kronologiskDatoOK = Arbejdsdage_Kalender.checkDateIsOK(startdato.replace(" ", ""), slutdato.replace(" ", "")) >= 0;
            if (!kronologiskDatoOK) {
                updateUdlejning.errorSlutdato(ERRORKRONOLOGISKDATO);
                errors++;
            }
        }

        boolean arbejdsDageOK = (arbejdsdage>0);
        if(!arbejdsDageOK){
            updateUdlejning.errorArbejdsdage(ERRORINGENARBEJDSDAGE);
            errors++;
        }

        boolean timeprisOK = timepris>0;
        System.out.println(timepris);
        System.out.println(timepris>0);
        if (!timeprisOK){
            updateUdlejning.errorTimepris(ERRORTIMEPRIS);
            errors++;
        }

        if(errors>0) {
            return false;
        }
        else {

            return true;
            //TODO opret udlejen/aftalen som objekt
            //TODO opret udlejen/aftalen på firebase
            //TODO evt. lav bekræftelsestoast eller returner boolean så der kan navigeres til en bekræftelsesside
        }

    }

    public void udregnPriser(int timeløn, int antalArbejdsdage, double gennemsnitstimer) {
        double subtotal = timeløn*gennemsnitstimer*antalArbejdsdage;
        double flexicuGebyr = (subtotal*2.5)/100;
        double total = subtotal+flexicuGebyr;
        updateUdlejning.opdaterSubtotal(subtotal);
        updateUdlejning.opdaterFlexicufee(flexicuGebyr);
        updateUdlejning.opdaterTotal(total);

    }

    public boolean kanUdregnePris(String timeprisString, String arbejdsdageString) {
        System.out.println(timeprisString + " HHHHHHH " + arbejdsdageString);
        if(timeprisString.length()>0 && !arbejdsdageString.equals("antal arb. dage")) {
            return true;
        }
            else
                return false;
    }

    //TODO diverse metoder der skal anvende DB og tjekke.
    interface UpdateUdlejning{
        void opdaterSubtotal(double værdi);
        void opdaterFlexicufee(double værdi);
        void opdaterTotal(double værdi);
        void opdaterAntalArbejdsdage(int dage);
        void errorMedarbejder(String errorMSG);
        void errorStartdato(String errorMSG);
        void errorSlutdato(String errorMSG);
        void errorTimepris(String errorMSG);
        void errorArbejdsdage(String errorMSG);
        //TODO evt. noget popup ved oprettelse eller andet.
    }
}
