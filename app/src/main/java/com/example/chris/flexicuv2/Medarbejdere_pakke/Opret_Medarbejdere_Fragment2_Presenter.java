package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.util.Patterns;

public class Opret_Medarbejdere_Fragment2_Presenter {


    private final String ERROREMAIL="EMAILFEJL";
    private final String ERRORTLF="TLFFEJL";
    private final String ERRORARBEJDSOMRåDER="ARBEJDSOMRÅDERFEJL";

    UpdateOpretMedarbejderFrag frag;



    public Opret_Medarbejdere_Fragment2_Presenter(UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag){
        frag = updateOpretMedarbejderFrag;
    }



    boolean korrektUdfyldtInformationFrag2(String email, String tlf, String arbejdsområder){
        int errors=0;

        boolean emailOK = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if(!emailOK) {
            frag.errorEmail(ERROREMAIL);
            errors++;
        }

        boolean tlfOK = checkStringOnlyNumbersAndLength(tlf, 8);
        if(!tlfOK){
            frag.errorTlf(ERRORTLF);
            errors++;
        }

        boolean arbejdsområderOK = arbejdsområder!=null;
        if(!arbejdsområderOK){
            frag.errorArbejdsområder(ERRORARBEJDSOMRåDER);
            errors++;
        }

        if(errors>0)
            return false;
        else{
            // TODO dbManager skal oprette medarbejderen
            return true;
        }
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

    interface UpdateOpretMedarbejderFrag{
        //Alle metoder til opdatering af variablene for en medarbejder
        void errorEmail(String errorMsg);
        void errorTlf(String errorMsg);
        void errorArbejdsområder(String errorMsg);
    }

}
