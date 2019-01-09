package com.example.chris.flexicuv2.opret_bruger;

import android.content.Context;
import android.util.Patterns;

public class NewUser_Presenter {
    private UpdateNewUser updateNewUser;
    public NewUser_Presenter(UpdateNewUser updateNewUser){
    this.updateNewUser = updateNewUser;
    }

    /**
     *
     * @return true if the information passed is correctly entered
     *          false if the information passed is not correctly entered
     */
    boolean korrektudfyldtInformation(String CVR, String Virksomhedsnavn, String adresse, String postNr, String by, String email1, String email2, String password, String password2, String privatoplysninger, boolean accepterbetingelser){
        boolean CVROK = checkCVR(CVR);
        boolean vshOK = !Virksomhedsnavn.isEmpty();
        boolean adresseOK = !adresse.isEmpty();
        boolean postNrOK = checkPostNr(postNr);
        boolean byOK = !by.isEmpty();
        boolean email = Patterns.EMAIL_ADDRESS.matcher(email1).matches();


        return true;
    }

    //TODO - Metode kan lægges sammen med checkCVR()
    private boolean checkPostNr(String postNr) {
        if(postNr.length() == 4){
            int ammNumbers = 0;
        for (int i = 0; i < 4; i++) {
            if (postNr.charAt(i) <= 9 && postNr.charAt(i) >= 0) {
                ammNumbers++;
            }
        }
        if(ammNumbers == 8)
            return true;
    }

            return false;
    }

    private boolean checkCVR(String cvr) {
        if(cvr.length() == 8) {
            int ammNumbers = 0;
            for (int i = 0; i < 8; i++) {
                if (cvr.charAt(i) <= 9 && cvr.charAt(i) >= 0) {
                ammNumbers++;
                }
            }
            if(ammNumbers == 8)
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
     * Interfacet implementeres af NewUser_akt for at kunne opdatere viewet fra presenteren
     */
    interface UpdateNewUser{
        void updateVirksomhedsNavn(String vsh_navn);
        void updateAdresse(String adresse);
        void updatePostNr(String postNr);
        void updateBy(String by);
        // Toast kan evt. erstattes af noget andet visuelt der fortæller brugeren hvor det går galt.
        void toastIfMissingOrWrongInformation(String displayedMessage);



    }
}
