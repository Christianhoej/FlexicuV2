package com.example.chris.flexicuv2.opret_bruger;

import android.content.Context;
import android.util.Patterns;

import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.Map;

public class Opret_bruger_Presenter_Frag2 implements  com.example.chris.flexicuv2.database.DBManager.CreateUserSuccess {

    private Opret_bruger_Presenter_Frag2.UpdateNewUser_Frag2 updateNewUser;

    private final String ERROREMAILFORM = "EMAILFORMFEJL";
    private final String ERROREMAILMATCHES = "EMAILMATCHESFEJL";
    private final String ERRORPASSWORDLENGTH = "PASSWORDLENGTHFEJL";
    private final String ERRORPASSWORDMATCHES = "PASSWORDMATCHESFEJL";
    private final String ERRORPRIVATOPLYSNINGER = "PRIVATOPLYSNINGERFEJL"; //TODO behøves den? - Button kan deaktiveres indtil den er tjekket af
    private final String ERRORACCEPTERVILKÅR = "ACCEPTERVILKÅRFEJL"; //TODO Behøves den?
    private final String BRUGEROPRETTET = "Bruger oprettet";
    private DBManager dbManager;
    private String cvr;
    private Map<String, String> map;
    private CVR_Opslag cvr_opslag;
    private Singleton singleton;


    public Opret_bruger_Presenter_Frag2(Opret_bruger_Presenter_Frag2.UpdateNewUser_Frag2 updateNewUser){
        this.updateNewUser = updateNewUser;
        dbManager = new DBManager();
        dbManager.setCreateUserSuccess(this);
        singleton = Singleton.getInstance();
    }

    /**
     * Metoden skal anvendes til at kontrollere at alle informationer er blevet tastet "tilstrækkeligt ind"
     * @param email1 Først email der tastes ind
     * @param email2 anden email der tastes ind
     * @param password : Første password der indtastes
     * @param password2 : Andet password der skal sikre brugeren har tastet rigtigt ind
     * @param privatoplysninger -1 if nothing is chosen, 1 if private, 2 if public
     * @param accepterbetingelser
     * @param context :
     * @return boolean true hvis alle informationer er blevet tastet godt nok ind.
     */
    boolean korrektudfyldtInformation(String email1, String email2, String password, String password2,
                                      int privatoplysninger, boolean accepterbetingelser, Context context) {
        int errors = 0;

        boolean emailOK = Patterns.EMAIL_ADDRESS.matcher(email1).matches();
        if(!emailOK) {
            updateNewUser.errorEmailForm(ERROREMAILFORM);
            errors++;
        }
        boolean emailMatchesOK = email1.equals(email2);
        if(!emailMatchesOK) {
            updateNewUser.errorEmailMatches(ERROREMAILMATCHES);
            errors++;
        }
        boolean passwordlengthOK = password.length() >= 6;
        if(!passwordlengthOK) {
            updateNewUser.errorPasswordLength(ERRORPASSWORDLENGTH);
            errors++;
        }
        boolean passwordMatchesOK = password.equals(password2);
        if(!passwordMatchesOK) {
            updateNewUser.errorPasswordMatches(ERRORPASSWORDMATCHES);
            errors++;
        }

        //TODO - Ingen check - Skal bare bruge værdien på privatoplysninger til at oprette (privat/offentlig)
        /*boolean privatoplysningerOK = privatoplysninger == -1;
        if(!privatoplysningerOK) {
            updateNewUser.errorPrivatoplysninger(ERRORPRIVATOPLYSNINGER);
            errors++;
        }
        */
        //TODO Behøves denne? - Kan deaktivere "opret"-knappen i NewUser_akt
        if(!accepterbetingelser) {
            updateNewUser.errorAcceptTerms(ERRORACCEPTERVILKÅR);
            errors++;
        }

        if (errors > 0 )
            return false;
        else {
            singleton.midlertidigBruger.setEmail(email1);
            System.out.println("1: " + singleton.midlertidigBruger.getVirksomhedsnavn());
            System.out.println("2: " + singleton.midlertidigBruger.getBrugerensNavn());
            System.out.println("3: " + singleton.midlertidigBruger.getAdresse());
            dbManager.createUserAuth(context, email1, password);
            dbManager.createBruger(singleton.midlertidigBruger);
            setMidlertidigBruger(email1, privatoplysninger);
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

    /**
     * Metode til at oprette bruger
     */
    void opretNyBruger(/*Indsæt alle oplysninger*/){

    }

    /**
     * Metode til at opretteVirksomhed
     */
    void opretVirksomhed(){

    }

    @Override
    public void userCreateSuccess(boolean success) {
        if(success){
            updateNewUser.newUserSuccess("Bruger er oprettet", success);
        }
        else{
            updateNewUser.newUserSuccess("Bruger er ikke oprettet", success);
        }
    }

    @Override
    public void failureMesssage(String message) {
        updateNewUser.newUserFailed(message);
    }

    /**
     * Interfacet implementeres af NewUser_Frag2 for at kunne opdatere viewet fra presenteren
     */
    interface UpdateNewUser_Frag2{

        void newUserSuccess(String displayedMessage, boolean success);
        void newUserFailed(String displayedMessage);
        void errorEmailForm(String errorMsg);
        void errorEmailMatches(String errorMsg);
        void errorPasswordLength(String errorMsg);
        void errorPasswordMatches(String errorMsg);
        void errorAcceptTerms(String errorMsg);
        void errorPrivatoplysninger(String errorMsg);

        void setEmail(String email);
        //void setKodeord(String kodeord);
        void setPrivatoplysninger(int privatoplysninger);

    }

    public void setMidlertidigBruger(String email/*, String kodeord*/, int privatoplysninger){
        singleton.midlertidigBruger.setEmail(email);
        //singleton.midlertidigBruger.setKodeord(kodeord);
        singleton.midlertidigBruger.setPrivatoplysninger(privatoplysninger);
    }

    public void udfyldFelter(){
        if(singleton.midlertidigBruger.getEmail() != null ) {
            updateNewUser.setEmail(singleton.midlertidigBruger.getEmail());
        }
        /*if(singleton.midlertidigBruger.getKodeord() != null) {
            frag.setKodeord(singleton.midlertidigBruger.getKodeord());
        }*/
        if(singleton.midlertidigBruger.getPrivatoplysninger() ==2){
            updateNewUser.setPrivatoplysninger(singleton.midlertidigBruger.getPrivatoplysninger());
        }
    }
}

