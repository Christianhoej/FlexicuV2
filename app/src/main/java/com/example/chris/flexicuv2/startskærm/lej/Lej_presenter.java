package com.example.chris.flexicuv2.startskærm.lej;

public class Lej_presenter {
    private UpdateLej updateLej;
    private final String ERRORMEDARBEJDER = "MEDARBEJDERFEJL";
    private final String ERRORSTARTDATO = "STARTDATOFEJL";
    private final String ERRORSLUTDATO = "SLUTDATOFEJL";
    private final String ERRORTIMEPRIS = "TIMEPRISFEJL";


    public Lej_presenter(UpdateLej updateLej){
        this.updateLej = updateLej;
    }


    public boolean checkKorrektUdfyldtInformation(String startdato, String slutdato, int timepris) {

        int errors = 0;


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
        boolean timeprisOK = timepris>0;
        if (!timeprisOK){
            updateLej.errorTimepris(ERRORTIMEPRIS);
            errors++;
        }

        if(errors>0) {
            return false;
        }
        else {
            updateLej.errorSlutdato(null);
            updateLej.errorStartdato(null);
            updateLej.errorTimepris(null);
            return true;
            //TODO opret udlejen/aftalen som objekt
            //TODO opret udlejen/aftalen på firebase
            //TODO evt. lav bekræftelsestoast eller returner boolean så der kan navigeres til en bekræftelsesside
        }

    }


    public void udregnPriser(String timepris, String arbejdsdage) {
        //TODO CHECK for det kun er tal
        int timeløn = Integer.parseInt(timepris);
        int antalArbejdsdage = Integer.parseInt(arbejdsdage);
        int subtotal = timeløn*antalArbejdsdage;
        double flexicuGebyr = subtotal*2.5;
        double total = subtotal+flexicuGebyr;
        updateLej.opdaterSubtotal(subtotal);
        updateLej.opdaterFlexicufee(flexicuGebyr);
        updateLej.opdaterTotal(total);

    }


    interface UpdateLej{
        void opdaterSubtotal(double værdi);
        void opdaterFlexicufee(double værdi);
        void opdaterTotal(double værdi);
        void errorStartdato(String errorMSG);
        void errorSlutdato(String errorMSG);
        void errorTimepris(String errorMSG);

    }
}
