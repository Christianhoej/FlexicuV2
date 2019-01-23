package com.example.chris.flexicuv2.startskærm.lej;

import android.content.Context;

import com.example.chris.flexicuv2.hjælpeklasser.Afstandsberegner;
import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;
import com.example.chris.flexicuv2.model.Singleton;

import java.io.IOException;

public class Lej_filtrer_Presenter {

    private UpdateFiltrer updateFiltrer;

    private final String ERRORKRONOLOGISKDATO = "ERRORKRONOLOGISKDATO";
    private final String ERRORINGENARBEJDSDAGE = "ERRORINGENARBEJDSDAGE";
    private final String ERRORADRESSE = "Der kunne ikke findes en adresse med de pågældende informationer.";
    private Singleton singleton;

    public Lej_filtrer_Presenter(UpdateFiltrer updateFiltrer){
        this.updateFiltrer = updateFiltrer;
        singleton = Singleton.getInstance();
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
        else {
            singleton.søgeFiltrering.setStartdato(startDato.replace(" ", ""));
            singleton.søgeFiltrering.setSlutdato(slutDato.replace(" ", ""));
            return true;
        }
    }
    public boolean checkAdresse(Context context, String vejnavn, String husnummer, String postNr, String by){
        try {
            String[] latiOglongi = Afstandsberegner.geolocate(context, vejnavn, husnummer, postNr, by).split(" ");
            singleton.søgeFiltrering.setLatitude(latiOglongi[0]);
            singleton.søgeFiltrering.setLongitude(latiOglongi[1]);
        } catch (IOException e) {
            updateFiltrer.errorAdresse(ERRORADRESSE);
        } catch(IndexOutOfBoundsException e){
            e.printStackTrace();
            updateFiltrer.errorAdresse(ERRORADRESSE);
        }
        return true;
    }

    public interface UpdateFiltrer{
         void errorKronologiskDato(String errorMSG);
         void errorIngenArbejdsDage(String errorMSG);
         void errorAdresse(String errorMSG);
         void sendToast(String msg);
         void setArbejdsområde(String[] arbejdsområde);

    }


}
