package com.example.chris.flexicuv2.startskærm.lej;

import android.content.Context;

import com.example.chris.flexicuv2.hjælpeklasser.Afstandsberegner;
import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;

import java.io.IOException;

public class Lej_filtrer_Presenter {

    private UpdateFiltrer updateFiltrer;

    private final String ERRORKRONOLOGISKDATO = "ERRORKRONOLOGISKDATO";
    private final String ERRORINGENARBEJDSDAGE = "ERRORINGENARBEJDSDAGE";
    private final String ERRORADRESSE = "Der kunne ikke findes en adresse med de pågældende informationer.";


    public Lej_filtrer_Presenter(UpdateFiltrer updateFiltrer){
        this.updateFiltrer = updateFiltrer;
    }
    public boolean checkDatoerErOK(String startDato, String slutDato){
        int errors = 0;
        boolean kronologiskOK = Arbejdsdage_Kalender.checkDateIsOK(startDato.replace(" ",""),slutDato.replace(" ", ""))>=0;
        if(!kronologiskOK){
            updateFiltrer.errorKronologiskDato(ERRORKRONOLOGISKDATO);
            errors++;
        }
        int arbDage = Arbejdsdage_Kalender.findArbejdsdage(startDato.replace(" ", ""), slutDato.replace(" ",""));
        if(!(arbDage>0)){
            updateFiltrer.errorIngenArbejdsDage(ERRORINGENARBEJDSDAGE);
            errors ++;
        }
        if (errors>0)
            return false;
        else
        return true;
    }
    public boolean checkAdresse(Context context, String vejnavn, String husnummer, String postNr, String by){
        try {
            String latiOglongi = Afstandsberegner.geolocate(context, vejnavn, husnummer, postNr, by);
            System.out.println(latiOglongi + " Adressen fundet");
        } catch (IOException e) {
            updateFiltrer.errorAdresse(ERRORADRESSE);
            //updateFiltrer.sendToast(ERRORADRESSE);
        } catch(IndexOutOfBoundsException e){
            e.printStackTrace();
            updateFiltrer.errorAdresse(ERRORADRESSE);
            //updateFiltrer.sendToast(ERRORADRESSE);
        }
        return true;
    }

    public interface UpdateFiltrer{
         void errorKronologiskDato(String errorMSG);
         void errorIngenArbejdsDage(String errorMSG);
         void errorAdresse(String errorMSG);
         void sendToast(String msg);

    }


}
