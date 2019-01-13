package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.util.Patterns;

import com.example.chris.flexicuv2.DBManager;

/**
 * @Author
 */
public class opretMedarbejder_presenter {

    private UpdateOpretMedarbejder updateOpretMedarbejder;
    private DBManager dbManager;

    private final String ERRORNAVN="NAVNFEJL";
    private final String ERRORKØN="KØNFEJL";
    private final String ERRORFøDSELSÅR="FØDSELSÅRFEJL";
    private final String ERROREMAIL="EMAILFEJL";
    private final String ERRORTLF="TLFFEJL";
    private final String ERRORARBEJDSOMRøDER="ARBEJDSOMRÅDERFEJL";

    /**
     * Konstruktør
     * @param updateOpretMedarbejder
     */
    public opretMedarbejder_presenter(UpdateOpretMedarbejder updateOpretMedarbejder) {
        this.updateOpretMedarbejder = updateOpretMedarbejder;
    }

    /**
     * TODO Skal kommentar mon tjekkes? Ikke hvis den er valgfri.
     */
    boolean korrektUdfyldtInformation(String navn, String køn, Integer fødselsår,
                                      String email, String tlf, String arbejdsområder, String kommentar){
        int errors=0;


        boolean navnOK = !navn.isEmpty();
        if(!navnOK){
            updateOpretMedarbejder.errorNavn(ERRORNAVN);
            errors++;
        }

        //Enten på denne måde eller med Janus's tjek om der er 4 cifre
        boolean fødselsårOK = 2005>fødselsår && fødselsår>1939;
        if(!fødselsårOK){
            updateOpretMedarbejder.errorFødselsår(ERRORFøDSELSÅR);
            errors++;
        }

        boolean emailOK = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if(!emailOK) {
            updateOpretMedarbejder.errorEmail(ERROREMAIL);
            errors++;
        }

        boolean tlfOK = checkStringOnlyNumbersAndLength(tlf, 8);
        if(!tlfOK){
            updateOpretMedarbejder.errorTlf(ERRORTLF);
            errors++;
        }

        boolean arbejdsområderOK = !arbejdsområder.isEmpty();
        if(!arbejdsområderOK){
            updateOpretMedarbejder.errorNavn(ERRORNAVN);
            errors++;
        }

        if(errors>0)
            return false;
        else{
            // TODO dbManager skal oprette medarbejderen
            return true;
        }
    }

    /**
     * Interfacet skal implementeres. Enten i Medarbejdere_skaerm, eller i de to fragmenter
     */
    interface UpdateOpretMedarbejder{
        //Alle metoder til opdatering af variablene for en medarbejder
        void errorNavn(String errorMsg);
        void errorKøn(String errorMsg);
        void errorFødselsår(String errorMsg);
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
