package com.example.chris.flexicuv2.opret_bruger;

import android.content.Context;
import android.util.Patterns;

import com.example.chris.flexicuv2.DBManager;

public class NewUser_Presenter {
    private UpdateNewUser updateNewUser;

    private final String ERRORMSGCVR = "CVRFEJL";
    private final String ERRORVIRKSOMHEDSNAVN="VSHNAVNFEJL";
    private final String ERRORADRESSE = "ADRESSEFEJL";
    private final String ERRORBY = "BYFEJL";

    private final String ERRORNAVN = "NAVENFEJL";
    private final String ERRORTLFNR = "TELEFONNUMMER";
    private final String ERRORTITEL =  "TITELFEJL";

    private final String ERROREMAILFORM = "EMAILFORMFEJL";
    private final String ERROREMAILMATCHES = "EMAILMATCHESFEJL";
    private final String ERRORPASSWORDLENGTH = "PASSWORDLENGTHFEJL";
    private final String ERRORPASSWORDMATCHES = "PASSWORDMATCHESFEJL";
    private final String ERRORPRIVATOPLYSNINGER = "PRIVATOPLYSNINGERFEJL"; //TODO behøves den? - Button kan deaktiveres indtil den er tjekket af
    private final String ERRORACCEPTERVILKÅR = "ACCEPTERVILKÅRFEJL"; //TODO Behøves den?
    private final String ERRORPOSTNR = "POSTNRFEJL";
    private final String BRUGEROPRETTET = "Bruger oprettet";
    private DBManager dbManager;



    public NewUser_Presenter(UpdateNewUser updateNewUser){
        this.updateNewUser = updateNewUser;
        dbManager = new DBManager();
    }

    /**
     * Metoden skal anvendes til at kontrollere at alle informationer er blevet tastet "tilstrækkeligt ind".
     *
     * @param CVR : virksomhedens cvr (8 cifre)
     * @param Virksomhedsnavn : virksomhedens navn
     * @param adresse : virksomhedens adresse
     * @param postNr : virksomhedens postnr
     * @param by : Virksomhedens by (tilhørende adresse)
     * @param email1 Først email der tastes ind
     * @param email2 anden email der tastes ind
     * @param brugerensNavn : Navnet på brugeren
     * @param brugerensTlf  : Brugerens tlf-nummer (8 cifre)
     * @param brugerensTitel : brugerens titel (i virksomheden)
     * @param password : Første password der indtastes
     * @param password2 : Andet password der skal sikre brugeren har tastet rigtigt ind
     * @param privatoplysninger -1 if nothing is chosen, 1 if private, 2 if public
     * @param accepterbetingelser
     * @param context :
     * @return boolean true hvis alle informationer er blevet tastet godt nok ind.
     */
    boolean korrektudfyldtInformation(String CVR, String Virksomhedsnavn, String adresse, String postNr, String by,String brugerensNavn, String brugerensTlf, String brugerensTitel, String email1, String email2, String password, String password2, int privatoplysninger, boolean accepterbetingelser, Context context) {
        int errors = 0;


        boolean CVROK = checkStringOnlyNumbersAndLength(CVR, 8);
        if (!CVROK) {
            updateNewUser.errorCVR(ERRORMSGCVR);
            errors++;
        }
        boolean vshOK = !Virksomhedsnavn.isEmpty();
        if (!vshOK) {
            updateNewUser.errorVirksomhedsnavn(ERRORVIRKSOMHEDSNAVN);
            errors++;
        }
        boolean adresseOK = !adresse.isEmpty();
        if (!adresseOK) {
            updateNewUser.errorAdresse(ERRORADRESSE);
            errors++;
        }
        boolean postNrOK = checkStringOnlyNumbersAndLength(postNr, 4);
        if (!postNrOK){
            updateNewUser.errorPostnr(ERRORPOSTNR);
        errors++;
        }
        boolean byOK = !by.isEmpty();
        if(!byOK) {
            updateNewUser.errorBy(ERRORBY);
            errors++;
        }


         boolean navnOK = brugerensNavn.length()>0;
        if (!navnOK){
            updateNewUser.errorNavn(ERRORNAVN);
            errors++;
        }
        boolean brugerensTlfOK = checkStringOnlyNumbersAndLength(brugerensTlf, 8);
        if(!brugerensTlfOK){
            updateNewUser.errorTlf(ERRORTLFNR);
            errors++;
        }
        boolean brugerTitelOK = brugerensTitel.length()>0;
        if(!brugerTitelOK){
            updateNewUser.errorTitel(ERRORTITEL);
        }


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
           // updateNewUser.toastTilBrugerOprettet(BRUGEROPRETTET);
            //dbManager.createUserAuth(context, email1, password);
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

    /**
     * Metode skal hente CVR oplysningerne, virksomhedsnavn, virksomhedsadresse, by og postnr.
     * @param CVR: 8 cifret cvr string
     */
    public void hentVirksomhedsoplysninger(String CVR) {
        updateNewUser.updateAdresse("Dette er min adresse");
        updateNewUser.updateVirksomhedsNavn("Janus Aps");
        updateNewUser.updatePostNr("2800");
        updateNewUser.updateBy("Herlev");
    }

    /**
     * Interfacet implementeres af NewUser_akt for at kunne opdatere viewet fra presenteren
     */
    interface UpdateNewUser{

        void updateVirksomhedsNavn(String vsh_navn);
        void updateAdresse(String adresse);
        void updatePostNr(String postNr);
        void updateBy(String by);

        void toastTilBrugerOprettet(String displayedMessage);

        void errorCVR(String errorMsg);
        void errorVirksomhedsnavn(String errorMsg);
        void errorAdresse(String errorMsg);
        void errorBy(String errorMsg);
        void errorPostnr(String errorMsg);

        void errorNavn(String errorMsg);
        void errorTlf(String errorMsg);
        void errorTitel(String errorMsg);
        void errorEmailForm(String errorMsg);
        void errorEmailMatches(String errorMsg);
        void errorPasswordLength(String errorMsg);
        void errorPasswordMatches(String errorMsg);
        void errorPrivatoplysninger(String errorMsg);
        void errorAcceptTerms(String errorMsg);




    }
}
