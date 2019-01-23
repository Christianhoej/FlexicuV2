package com.example.chris.flexicuv2.startskærm.lej;

import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;

public class Lej_presenter {
    private UpdateLej updateLej;
    private final String ERRORKRONOLOGISKDATO = "Den valgte slutdato falder før startdatoen";
    private final String ERRORSTARTDATO = "STARTDATOFEJL";
    private final String ERRORSLUTDATO = "SLUTDATOFEJL";
    private final String ERRORTIMEPRIS = "TIMEPRISFEJL";
    private final String ERRORINGENARBEJDSDAGE = "Den valgte periode har ingen arbejdsdage";


    public Lej_presenter(UpdateLej updateLej){
        this.updateLej = updateLej;
    }


    /**
     * Metoden anvendes til at finde totale antal arbejdsdage i perioden
     * @param startdato
     * @param slutdato
     */
    public void udregnArbejdsdage(String startdato, String slutdato, boolean lejer){
        startdato = startdato.replace(" ", "");
        slutdato = slutdato.replace(" ","");

        int arbDage = Arbejdsdage_Kalender.findArbejdsdage(startdato, slutdato);
        if(arbDage<0) {
            arbDage = 0;
        }
        if(lejer) {
            updateLej.opdaterAntalArbejdsdageLejer(arbDage);
        }
        else{
            updateLej.opdaterAntalArbejdsdageUdlejer(arbDage);
        }
    }

    public boolean checkKorrektUdfyldtInformation(String startdato, String slutdato, int timepris, int arbejdsdage) {

        int errors = 0;

        updateLej.errorSlutdato(null);
        updateLej.errorStartdato(null);
        updateLej.errorTimepris(null);
        updateLej.errorArbejdsdage(null);

        boolean startdatoOK = startdato.equals(" dd / mm / yyyy ");
        if(startdatoOK){
            updateLej.errorStartdato(ERRORSTARTDATO);
            errors++;
        }
        boolean slutdatoOK = slutdato.equals(" dd / mm / yyyy ");
        if(slutdatoOK){
            updateLej.errorSlutdato(ERRORSLUTDATO);
            errors++;
        }

        if (errors ==0) {
            boolean kronologiskDatoOK = Arbejdsdage_Kalender.checkDateIsOK(startdato.replace(" ", ""), slutdato.replace(" ", "")) >= 0;
            if (!kronologiskDatoOK) {
                updateLej.errorSlutdato(ERRORKRONOLOGISKDATO);
                errors++;
            }
        }

        boolean arbejdsDageOK = (arbejdsdage>0);
        if(!arbejdsDageOK){
            updateLej.errorArbejdsdage(ERRORINGENARBEJDSDAGE);
            errors++;
        }

        boolean timeprisOK = timepris>0;
        if (!timeprisOK){
            updateLej.errorTimepris(ERRORTIMEPRIS);
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
            updateLej.opdaterSubtotalLejer(subtotal);
            updateLej.opdaterFlexicufeeLejer(flexicuGebyr);
            updateLej.opdaterTotalLejer(total);
        }
        else{
            updateLej.opdaterSubtotalUdlejer(subtotal);
            updateLej.opdaterFlexicufeeUdlejer(flexicuGebyr);
            updateLej.opdaterTotalUdlejer(total);
        }
    }


    interface UpdateLej{
        void opdaterSubtotalLejer(double værdi);
        void opdaterFlexicufeeLejer(double værdi);
        void opdaterTotalLejer(double værdi);
        void errorStartdato(String errorMSG);
        void errorSlutdato(String errorMSG);
        void errorTimepris(String errorMSG);
        void errorArbejdsdage(String errorMSG);
        void opdaterAntalArbejdsdageLejer(int dage);
        void opdaterAntalArbejdsdageUdlejer(int dage);
        void opdaterSubtotalUdlejer(double værdi);
        void opdaterFlexicufeeUdlejer(double værdi);
        void opdaterTotalUdlejer(double værdi);

    }
}
