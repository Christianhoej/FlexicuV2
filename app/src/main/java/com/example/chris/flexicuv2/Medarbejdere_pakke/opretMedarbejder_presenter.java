package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.util.Patterns;

import com.example.chris.flexicuv2.DBManager;

/**
 * @Author
 */
public class opretMedarbejder_presenter {

    private UpdateOpretMedarbejderAkt updateOpretMedarbejderAkt;
    private UpdateOpretMedarbejderFrag1 updateOpretMedarbejderFrag1;
    private UpdateOpretMedarbejderFrag2 updateOpretMedarbejderFrag2;
    private final static opretMedarbejder_presenter INSTANCE = new opretMedarbejder_presenter();

    private DBManager dbManager;



    public void setUpdateOpretMedarbejderAkt(UpdateOpretMedarbejderAkt updateOpretMedarbejderAkt) {
        this.updateOpretMedarbejderAkt = updateOpretMedarbejderAkt;
    }

    public void setUpdateOpretMedarbejderFrag1(UpdateOpretMedarbejderFrag1 updateOpretMedarbejderFrag1) {
        this.updateOpretMedarbejderFrag1 = updateOpretMedarbejderFrag1;
    }

    public void setUpdateOpretMedarbejderFrag2(UpdateOpretMedarbejderFrag2 updateOpretMedarbejderFrag2) {
        this.updateOpretMedarbejderFrag2 = updateOpretMedarbejderFrag2;
    }



    private final String ERRORNAVN="NAVNFEJL";
    private final String ERRORKØN="KØNFEJL";
    private final String ERRORFøDSELSÅR="FØDSELSÅRFEJL";
    private final String ERROREMAIL="EMAILFEJL";
    private final String ERRORTLF="TLFFEJL";
    private final String ERRORARBEJDSOMRåDER="ARBEJDSOMRÅDERFEJL";


    private opretMedarbejder_presenter() {
    }
    public static final opretMedarbejder_presenter getInstance(){

        return INSTANCE;
    }

    /**
     * TODO Skal kommentar mon tjekkes? Ikke hvis den er valgfri.
     */
    boolean korrektUdfyldtInformationFrag1(String navn, String køn, Integer fødselsår){
        int errors=0;


        boolean navnOK = !navn.isEmpty();
        if(!navnOK){
            updateOpretMedarbejderFrag1.errorNavn(ERRORNAVN);
            errors++;
        }
        //TODO Skal laves
        boolean kønOK = !køn.isEmpty();
        if(!navnOK){
            updateOpretMedarbejderFrag1.errorNavn(ERRORNAVN);
            errors++;
        }

        //Enten på denne måde eller med Janus's tjek om der er 4 cifre
        boolean fødselsårOK = 2005>fødselsår && fødselsår>1939;
        if(!fødselsårOK){
            updateOpretMedarbejderFrag1.errorFødselsår(ERRORFøDSELSÅR);
            errors++;
        }

        if(errors>0)
            return false;
        else{
            // TODO dbManager skal oprette medarbejderen
            return true;
        }
    }

    boolean korrektUdfyldtInformationFrag2(String email, String tlf, String arbejdsområder){
        int errors=0;

        boolean emailOK = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if(!emailOK) {
            updateOpretMedarbejderFrag2.errorEmail(ERROREMAIL);
            errors++;
        }

        boolean tlfOK = checkStringOnlyNumbersAndLength(tlf, 8);
        if(!tlfOK){
            updateOpretMedarbejderFrag2.errorTlf(ERRORTLF);
            errors++;
        }

        boolean arbejdsområderOK = !arbejdsområder.isEmpty();
        if(!arbejdsområderOK){
            updateOpretMedarbejderFrag2.errorArbejdsområder(ERRORARBEJDSOMRåDER);
            errors++;
        }

        if(errors>0)
            return false;
        else{
            // TODO dbManager skal oprette medarbejderen
            return true;
        }
    }

    interface UpdateOpretMedarbejderAkt{

    }

    public void setNavn() {
        updateOpretMedarbejderFrag1.errorNavn(" ");
    }

    /**
     * Interfacet skal implementeres. Enten i Medarbejdere_skaerm, eller i de to fragmenter
     */
    interface UpdateOpretMedarbejderFrag1{
        //Alle metoder til opdatering af variablene for en medarbejder
        void errorNavn(String errorMsg);
        void errorKøn(String errorMsg);
        void errorFødselsår(String errorMsg);
    }

    interface UpdateOpretMedarbejderFrag2{
        //Alle metoder til opdatering af variablene for en medarbejder
        void errorEmail(String errorMsg);
        void errorTlf(String errorMsg);
        void errorArbejdsområder(String errorMsg);
    }

    private boolean checkStringOnlyNumbersAndLength(String cvr, int stringlength) {
        if(cvr.length() == stringlength) {
            int ammNumbers = 0;
            for (int i = 0; i < stringlength; i++) {
                if (cvr.charAt(i) <= '9' && cvr.charAt(i) >= '0') {
                    ammNumbers++;
                }
            }
            if(ammNumbers == stringlength)
                return true;
        }

        return false;
    }

}
