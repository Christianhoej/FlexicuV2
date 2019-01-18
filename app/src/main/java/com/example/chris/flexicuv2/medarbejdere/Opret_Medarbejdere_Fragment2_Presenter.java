package com.example.chris.flexicuv2.medarbejdere;

import android.util.Patterns;

import com.example.chris.flexicuv2.model.Singleton;

public class Opret_Medarbejdere_Fragment2_Presenter {


    private final String ERROREMAIL="EMAILFEJL";
    private final String ERRORTLF="TLFFEJL";
    private final String ERRORARBEJDSOMRåDER="ARBEJDSOMRÅDERFEJL";
    private Singleton singleton;
    private UpdateOpretMedarbejderFrag frag;



    public Opret_Medarbejdere_Fragment2_Presenter(UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag){
        frag = updateOpretMedarbejderFrag;
        singleton = Singleton.getInstance();
        System.out.println("NAVN: " + singleton.midlertidigMedarbejder.getNavn());
        System.out.println("KØN: " + singleton.midlertidigMedarbejder.getKøn());
        System.out.println("Fødselsår: " + singleton.midlertidigMedarbejder.getFødselsår());
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
            setMidlertidigMedarbejder(email, tlf, arbejdsområder);
            return true;
        }
    }

    public void setMidlertidigMedarbejder(String email, String tlf, String arbejdsområder){
        singleton.midlertidigMedarbejder.setEmail(email);
        singleton.midlertidigMedarbejder.setTlfnr(tlf);
        singleton.midlertidigMedarbejder.setArbejdsomraade(arbejdsområder);
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
        void setEmail(String email);
        void setTlf(String tlf);
        void setArbejdsområde(String arbejdsområde);
    }

    public void udfyldFelter(){
        if(singleton.midlertidigMedarbejder.getEmail() != null ) {
            frag.setEmail(singleton.midlertidigMedarbejder.getEmail());
        }
        if(singleton.midlertidigMedarbejder.getTlfnr() != null) {
            frag.setTlf(singleton.midlertidigMedarbejder.getTlfnr());
        }
        if(singleton.midlertidigMedarbejder.getArbejdsomraade() != null){
            frag.setArbejdsområde(singleton.midlertidigMedarbejder.getArbejdsomraade());
        }
    }

}
