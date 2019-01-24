package com.example.chris.flexicuv2.startskærm.indbakke.forhandling;

import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;

public class Forhandling_presenter {
    private UpdateForhandling updateForhandling;
    private final String ERRORKRONOLOGISKDATO = "Den valgte slutdato falder før startdatoen";
    private final String ERRORSTARTDATO = "STARTDATOFEJL";
    private final String ERRORSLUTDATO = "SLUTDATOFEJL";
    private final String ERRORTIMEPRIS = "TIMEPRISFEJL";
    private final String ERRORINGENARBEJDSDAGE = "Den valgte periode har ingen arbejdsdage";
    private int arbDage;


    public Forhandling_presenter(UpdateForhandling updateForhandling){
        this.updateForhandling = updateForhandling;
    }


    /**
     * Metoden anvendes til at finde totale antal arbejdsdage i perioden
     * @param startdato
     * @param slutdato
     */
    public void udregnArbejdsdage(String startdato, String slutdato, boolean lejer){
        startdato = startdato.replace(" ", "");
        slutdato = slutdato.replace(" ","");

        arbDage = Arbejdsdage_Kalender.findArbejdsdage(startdato, slutdato);
        if(arbDage<0) {
            arbDage = 0;
        }
        if(lejer) {
            updateForhandling.opdaterAntalArbejdsdageRediger(arbDage);
        }
        else{
            updateForhandling.opdaterAntalArbejdsdageFast(arbDage);
        }
    }

    public boolean checkKorrektUdfyldtInformation(String startdato, String slutdato, int timepris, int arbejdsdage) {

        int errors = 0;

        updateForhandling.errorSlutdato(null);
        updateForhandling.errorStartdato(null);
        updateForhandling.errorTimepris(null);
        updateForhandling.errorArbejdsdage(null);

        boolean startdatoOK = startdato.equals(" dd / mm / yyyy ");
        if(startdatoOK){
            updateForhandling.errorStartdato(ERRORSTARTDATO);
            errors++;
        }
        boolean slutdatoOK = slutdato.equals(" dd / mm / yyyy ");
        if(slutdatoOK){
            updateForhandling.errorSlutdato(ERRORSLUTDATO);
            errors++;
        }

        if (errors ==0) {
            boolean kronologiskDatoOK = Arbejdsdage_Kalender.checkDateIsOK(startdato.replace(" ", ""), slutdato.replace(" ", "")) >= 0;
            if (!kronologiskDatoOK) {
                updateForhandling.errorSlutdato(ERRORKRONOLOGISKDATO);
                errors++;
            }
        }

        boolean arbejdsDageOK = (arbejdsdage>0);
        if(!arbejdsDageOK){
            updateForhandling.errorArbejdsdage(ERRORINGENARBEJDSDAGE);
            errors++;
        }

        boolean timeprisOK = timepris>0;
        if (!timeprisOK){
            updateForhandling.errorTimepris(ERRORTIMEPRIS);
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


    public void udregnPriser(int timeløn, int antalArbejdsdage, double gennemsnitstimer, boolean lejer) {
        double subtotal = timeløn*gennemsnitstimer*antalArbejdsdage;
        double flexicuGebyr = (subtotal*2.5)/100;
        double total = subtotal+flexicuGebyr;
        if(lejer) {
            updateForhandling.opdaterSubtotalRediger(subtotal);
            updateForhandling.opdaterFlexicufeeRediger(flexicuGebyr);
            updateForhandling.opdaterTotalRediger(total);
        }
        else{
            updateForhandling.opdaterSubtotalFast(subtotal);
            updateForhandling.opdaterFlexicufeeFast(flexicuGebyr);
            updateForhandling.opdaterTotalFast(total);
        }
    }

    public void updateFaste(boolean lejer, String startDato, String slutDato, String timepris, boolean egetVærktøj){
        updateForhandling.opdaterStartDatoFast(startDato);
        updateForhandling.opdaterSlutDatoFast(slutDato);
        updateForhandling.opdaterTimePrisFast(timepris);
        udregnArbejdsdage(startDato, slutDato, lejer);
        System.out.println(startDato + ", " + slutDato);
        udregnPriser(Integer.parseInt(timepris), arbDage, 7.4, lejer);
        if(egetVærktøj){
            updateForhandling.opdaterEgetVærktøjFast("Ja");
        }
        else {
            updateForhandling.opdaterEgetVærktøjFast("Nej");
        }

    }
    public void updateRediger(boolean lejer, String startDato, String slutDato, String timepris, boolean egetVærktøj){
        updateForhandling.opdaterStartDatoRediger(startDato);
        updateForhandling.opdaterSlutDatoRediger(slutDato);
        updateForhandling.opdaterTimePrisRediger(timepris);
        System.out.println(startDato + ", " + slutDato);
        udregnArbejdsdage(startDato, slutDato, lejer);
        udregnPriser(Integer.parseInt(timepris), arbDage, 7.4, lejer);
        if(egetVærktøj){
            updateForhandling.opdaterEgetVærktøjRediger("Ja");
        }
        else {
            updateForhandling.opdaterEgetVærktøjRediger("Nej");
        }
    }

    public void updateKnap(String startDatoFast, String slutDatoFast, String prisFast, boolean egetVærktøjFast, String startDatoRediger, String slutDatoRediger, String prisRediger, boolean egetVærktøjRediger){
        if(startDatoFast.equals(startDatoRediger) && slutDatoFast.equals(slutDatoRediger) && prisFast.equals(prisRediger) && egetVærktøjFast == egetVærktøjRediger){
            updateForhandling.opdaterKnap("Godkend");
        }
        else {
            updateForhandling.opdaterKnap("Send modtilbud");
        }
    }


    interface UpdateForhandling{
        void errorStartdato(String errorMSG);
        void errorSlutdato(String errorMSG);
        void errorTimepris(String errorMSG);
        void errorArbejdsdage(String errorMSG);

        void opdaterAntalArbejdsdageRediger(int dage);
        void opdaterSubtotalRediger(double værdi);
        void opdaterFlexicufeeRediger(double værdi);
        void opdaterTotalRediger(double værdi);
        void opdaterStartDatoRediger(String startDato);
        void opdaterSlutDatoRediger(String slutdato);
        void opdaterTimePrisRediger(String timepris);
        void opdaterEgetVærktøjRediger(String egetVærktøj);

        void opdaterAntalArbejdsdageFast(int dage);
        void opdaterSubtotalFast(double værdi);
        void opdaterFlexicufeeFast(double værdi);
        void opdaterTotalFast(double værdi);
        void opdaterStartDatoFast(String startDato);
        void opdaterSlutDatoFast(String slutdato);
        void opdaterTimePrisFast(String timepris);
        void opdaterEgetVærktøjFast(String egetVærktøj);

        void opdaterKnap(String knapText);
    }
}
