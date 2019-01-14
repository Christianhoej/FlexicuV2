package com.example.chris.flexicuv2.opret_bruger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Patterns;

import com.example.chris.flexicuv2.DBManager;
import com.example.chris.flexicuv2.model.Bruger;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.Map;

public class NewUser_Presenter_Frag1 {

    private NewUser_Presenter_Frag1.UpdateNewUser_Frag1 updateNewUser;

    private final String ERRORMSGCVR = "CVRFEJL";
    private final String ERRORVIRKSOMHEDSNAVN="VSHNAVNFEJL";
    private final String ERRORADRESSE = "ADRESSEFEJL";
    private final String ERRORBY = "BYFEJL";
    private final String ERRORPOSTNR = "POSTNRFEJL";

    private final String ERRORNAVN = "NAVENFEJL";
    private final String ERRORTLFNR = "TELEFONNUMMER";
    private final String ERRORTITEL =  "TITELFEJL";


    private DBManager dbManager;
    private AsyncHentCVR async;
    private String cvr;
    private Map<String, String> map;
    private CVR_Opslag cvr_opslag;
    private Singleton singleton;



    public NewUser_Presenter_Frag1(NewUser_Presenter_Frag1.UpdateNewUser_Frag1 updateNewUser){
        this.updateNewUser = updateNewUser;
        dbManager = new DBManager();
        singleton = Singleton.getInstance();
    }

    /**
     * Metoden skal anvendes til at kontrollere at alle informationer er blevet tastet "tilstrækkeligt ind".
     *
     * @param CVR : virksomhedens cvr (8 cifre)
     * @param virksomhedsnavn : virksomhedens navn
     * @param adresse : virksomhedens adresse
     * @param postNr : virksomhedens postnr
     * @param by : Virksomhedens by (tilhørende adresse)
     * @param brugerensNavn : Navnet på brugeren
     * @param brugerensTlf  : Brugerens tlf-nummer (8 cifre)
     * @param brugerensTitel : brugerens titel (i virksomheden)
     * @param context :
     * @return boolean true hvis alle informationer er blevet tastet godt nok ind.
     */
    boolean korrektudfyldtInformation(String CVR, String virksomhedsnavn, String adresse, String postNr,
                                      String by,String brugerensNavn, String brugerensTlf,
                                      String brugerensTitel, Context context) {
        int errors = 0;

        boolean CVROK = checkStringOnlyNumbersAndLength(CVR, 8);
        if (!CVROK) {
            updateNewUser.errorCVR(ERRORMSGCVR);
            errors++;
        }
        boolean vshOK = !virksomhedsnavn.isEmpty();
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

        if (errors > 0 )
            return false;
        else {
            singleton.setBruger(new Bruger());
            singleton.getBruger().setVirksomhedCVR(CVR);
            singleton.getBruger().setVirksomhedsnavn(virksomhedsnavn);
            singleton.getBruger().setAdresse(adresse);
            singleton.getBruger().setPostnr(postNr);
            singleton.getBruger().setBy(by);
            singleton.getBruger().setBrugerensNavn(brugerensNavn);
            singleton.getBruger().setTlfnr(brugerensTlf);
            singleton.getBruger().setTitel(brugerensTitel);
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
        //TODO må ikke være i main thread
        cvr = CVR;
        System.out.println("Her kommer jeg"); //TODO
        async = new AsyncHentCVR();
        async.execute();
/*        cvr_opslag = new CVR_Opslag();
        map = cvr_opslag.getResult(CVR);

        updateNewUser.updateAdresse(map.get(cvr_opslag.getAdresseString()));
        updateNewUser.updateVirksomhedsNavn(map.get(cvr_opslag.getVirksomhedsNavnString()));
        updateNewUser.updatePostNr(map.get(cvr_opslag.getPostNrString()));
        updateNewUser.updateBy(map.get(cvr_opslag.getByString()));
*/
    }

    private class AsyncHentCVR extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... param) {
            try {
                cvr_opslag = new CVR_Opslag();
                System.out.println("her er jeg også!!!!!!!!!!!!!!!");//TODO
                map = cvr_opslag.getResult(NewUser_Presenter_Frag1.this.cvr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            System.out.println(map.get(cvr_opslag.getAdresseString()) + "Jeg kan finde den!");
            updateNewUser.updateAdresse(map.get(cvr_opslag.getAdresseString()));
            updateNewUser.updateVirksomhedsNavn(map.get(cvr_opslag.getVirksomhedsNavnString()));
            updateNewUser.updatePostNr(map.get(cvr_opslag.getPostNrString()));
            updateNewUser.updateBy(map.get(cvr_opslag.getByString()));
        }
    }

    /**
     * Interfacet implementeres af NewUser_fragment_1 for at kunne opdatere viewet fra presenteren
     */
    interface UpdateNewUser_Frag1{

        void updateVirksomhedsNavn(String vsh_navn);
        void updateAdresse(String adresse);
        void updatePostNr(String postNr);
        void updateBy(String by);

        void errorCVR(String errorMsg);
        void errorVirksomhedsnavn(String errorMsg);
        void errorAdresse(String errorMsg);
        void errorBy(String errorMsg);
        void errorPostnr(String errorMsg);

        void errorNavn(String errorMsg);
        void errorTlf(String errorMsg);
        void errorTitel(String errorMsg);
    }
}

