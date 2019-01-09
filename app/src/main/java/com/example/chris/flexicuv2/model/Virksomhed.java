package com.example.chris.flexicuv2.model;

import java.util.ArrayList;

public class Virksomhed {

    private String virksomhedCVR;
    private String virksomhedID;
    private String virksomhedsnavn, adresse, postnr;
    private ArrayList<String> medarbejdere = new ArrayList<String>();


    public String getVirksomhedID() {
        return virksomhedID;
    }

    public void setVirksomhedID(String virksomhedID) {
        this.virksomhedID = virksomhedID;
    }


    public void setVirksomhedsnavn(String virksomhedsnavn) {
        this.virksomhedsnavn = virksomhedsnavn;
    }

    public String getVirksomhedsnavn() {
        return virksomhedsnavn;
    }

    public void setVirksomhedCVR(String virksomhedCVR) {
        this.virksomhedCVR = virksomhedCVR;
    }

    public String getVirksomhedCVR() {
        return virksomhedCVR;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getPostnr() {
        return postnr;
    }

    public void addMedarbejdere(Medarbejder medarbejder){
        medarbejdere.add(medarbejder.getMedarbejderID());
    }

    public void removeMedarbejdere(Medarbejder medarbejder){
        medarbejdere.remove(medarbejder);
    }

    public ArrayList<String> getMedarbejdere() {
        return medarbejdere;
    }

    public void setMedarbejdere(ArrayList<Medarbejder> medarbejdere) {
        for(int i = 0; i<medarbejdere.size(); i++){
            this.medarbejdere.add(medarbejdere.get(i).getMedarbejderID());
        }
    }

}