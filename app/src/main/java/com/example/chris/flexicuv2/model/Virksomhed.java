package com.example.chris.flexicuv2.model;

import java.util.ArrayList;

public class Virksomhed {

    private String virksomhedCVR;
    private String virksomhedKey;
    private String virksomhedsnavn, adresse, postnr;
    private ArrayList<Bruger> brugere = new ArrayList<Bruger>();
    private ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();


    public String getVirksomhedKey() {
        return virksomhedKey;
    }

    public void setVirksomhedKey(String virksomhedKey) {
        this.virksomhedKey = virksomhedKey;
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

    public void addBruger(Bruger bruger){
        brugere.add(bruger);
    }

    public void removeBruger(Bruger bruger){
        brugere.remove(bruger);
    }

    public void addMedarbejdere(Medarbejder medarbejder){
        medarbejdere.add(medarbejder);
    }

    public void removeMedarbejdere(Medarbejder medarbejder){
        medarbejdere.remove(medarbejder);
    }

    public ArrayList<Bruger> getBrugere() {
        return brugere;
    }

    public void setBrugere(ArrayList<Bruger> brugere) {
        this.brugere = brugere;
    }

    public ArrayList<Medarbejder> getMedarbejdere() {
        return medarbejdere;
    }

    public void setMedarbejdere(ArrayList<Medarbejder> medarbejdere) {
        this.medarbejdere = medarbejdere;
    }

}