package com.example.chris.flexicuv2.startskærm.udlej;

class Udlejning_Presenter {
    private UpdateUdlejning updateUdlejning;
    private final String ERRORMEDARBEJDER = "MEDARBEJDERFEJL";
    private final String ERRORSTARTDATO = "STARTDATOFEJL";
    private final String ERRORSLUTDATO = "SLUTDATOFEJL";
    private final String ERRORTIMEPRIS = "TIMEPRISFEJL";

    public Udlejning_Presenter(UpdateUdlejning updateUdlejning){
        this.updateUdlejning = updateUdlejning;
    }



    public void checkKorrektUdfyldtInformation(String medarbejder, String startdato, String slutdato, int arbejdsdage, int timepris, String egetVærktøj, String kommentar) {

        int errors = 0;

        boolean medarbejderOK = !(medarbejder.equals("")); //TODO sæt tekst på spinneren til noget der kan genkendes
        if(!medarbejderOK)
        {
            updateUdlejning.errorMedarbejder(ERRORMEDARBEJDER);
            errors++;
        }
        boolean startdatoOK = startdato!=null || startdato!= "";
        if(!startdatoOK){
            updateUdlejning.errorStartdato(ERRORSTARTDATO);
            errors++;
        }
        boolean slutdatoOK = slutdato!=null || startdato!= "";
        if(!slutdatoOK){
            updateUdlejning.errorSlutdato(ERRORSLUTDATO);
            errors++;
        }
        boolean timeprisOK = timepris>0;
        if (!timeprisOK){
            updateUdlejning.errorTimepris(ERRORTIMEPRIS);
            errors++;
        }

        if(errors==0){
            //TODO opret udlejen/aftalen som objekt
            //TODO opret udlejen/aftalen på firebase
            //TODO evt. lav bekræftelsestoast eller returner boolean så der kan navigeres til en bekræftelsesside
        }

    }

    public void udregnPriser(int timeløn, int antalArbejdsdage) {
        //TODO CHECK for det kun er tal
        int subtotal = timeløn*antalArbejdsdage;
        double flexicuGebyr = subtotal*2.5;
        double total = subtotal+flexicuGebyr;
        updateUdlejning.opdaterSubtotal(subtotal);
        updateUdlejning.opdaterFlexicufee(flexicuGebyr);
        updateUdlejning.opdaterTotal(total);

    }

    //TODO diverse metoder der skal anvende DB og tjekke.
    interface UpdateUdlejning{
        void opdaterSubtotal(double værdi);
        void opdaterFlexicufee(double værdi);
        void opdaterTotal(double værdi);
        void opdaterAtalArbejdsdage(int dage);
        void errorMedarbejder(String errorMSG);
        void errorStartdato(String errorMSG);
        void errorSlutdato(String errorMSG);
        void errorTimepris(String errorMSG);
        //TODO evt. noget popup ved oprettelse eller andet.
    }
}
