package com.example.chris.flexicuv2.startskærm.udlej;

import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;


/**
 * @Athour Janus
 */
class Udlejning_Presenter {
    private final String ERRORKRONOLOGISKDATO = "Den valgte slutdato falder før startdatoen";
    private UpdateUdlejning updateUdlejning;
    private final String ERRORMEDARBEJDER = "MEDARBEJDERFEJL";
    private final String ERRORSTARTDATO = "STARTDATOFEJL";
    private final String ERRORSLUTDATO = "SLUTDATOFEJL";
    private final String ERRORTIMEPRIS = "TIMEPRISFEJL";
    private final String ERRORINGENARBEJDSDAGE = "Den valgte periode har ingen arbejdsdage";

    private Singleton singleton;

    public Udlejning_Presenter(UpdateUdlejning updateUdlejning){
        this.updateUdlejning = updateUdlejning;
        singleton = Singleton.getInstance();
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
           /* if(singleton.midlertidigAftale==null) {
                singleton.midlertidigAftale = new Aftale();
            }
            singleton.midlertidigAftale.setUdlejerStartDato(startdato);
            singleton.midlertidigAftale.setUdlejerSlutDato(slutdato);
            singleton.midlertidigAftale.setTimepris(timepris);
            singleton.midlertidigAftale.setUdlejKommentar(kommentar);*/
        }

        boolean arbejdsDageOK = (arbejdsdage>0);
        if(!arbejdsDageOK){
            updateUdlejning.errorArbejdsdage(ERRORINGENARBEJDSDAGE);
            errors++;
        }

        boolean timeprisOK = timepris>0;
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
        void setStartDato(String startDato);
        void setSlutDato(String slutDato);
        void setTimepris(int timepris);
        void setVærktøj(Boolean værktøj);
        void setKommentar(String kommentar);
        void setMedarbejder(Medarbejder medarbejder);
    }

    public void udfyldFelter(){
        if(singleton.midlertidigAftale !=null){
            updateUdlejning.setStartDato(singleton.midlertidigAftale.getStartDato());
            updateUdlejning.setSlutDato(singleton.midlertidigAftale.getSlutDato());
            updateUdlejning.setTimepris(Integer.parseInt(singleton.midlertidigAftale.getTimePris()));
            updateUdlejning.setVærktøj(singleton.midlertidigAftale.isEgetVærktøj());
            updateUdlejning.setKommentar(singleton.midlertidigAftale.getKommentar());
            //updateUdlejning.setMedarbejder(singleton.midlertidigMedarbejder);
        }
    }
}
