package com.example.chris.flexicuv2.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Virksomhed {

    private String virksomhedID;
    private String virksomhedsnavn, adresse, postnr;
    private ArrayList<Bruger> brugere = new ArrayList<Bruger>();
    private ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();


    public void setVirksomhedsnavn(String virksomhedsnavn) {
        this.virksomhedsnavn = virksomhedsnavn;
    }

    public String getVirksomhedsnavn() {
        return virksomhedsnavn;
    }

    public void setVirksomhedID(String virksomhedID) {
        this.virksomhedID = virksomhedID;
    }

    public String getVirksomhedID() {
        return virksomhedID;
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
}