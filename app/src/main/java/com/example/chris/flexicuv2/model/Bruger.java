package com.example.chris.flexicuv2.model;
/**
 * @Author Gunn
 */
import java.util.ArrayList;

public class Bruger {
    private String email;
    private String brugerensNavn;
    private String tlfnr;
    private String titel;
    private String brugerID;
    private String virksomhedCVR;
    private String by;
    private String virksomhedsnavn, adresse, postnr;

    public int getPrivatoplysninger() {
        return privatoplysninger;
    }

    public void setPrivatoplysninger(int privatoplysninger) {
        this.privatoplysninger = privatoplysninger;
    }

    private int privatoplysninger;
    private ArrayList<String> medarbejderID = new ArrayList<String>();

    //TODO opret kodeord og privatoplysninger


    public String getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(String brugerID) {
        this.brugerID = brugerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrugerensNavn() {
        return brugerensNavn;
    }

    public void setBrugerensNavn(String brugerensNavn) {
        this.brugerensNavn = brugerensNavn;
    }

    public String getTlfnr() {
        return tlfnr;
    }

    public void setTlfnr(String tlfnr) {
        this.tlfnr = tlfnr;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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
        medarbejderID.add(medarbejder.getMedarbejderID());
    }

    public void removeMedarbejdere(Medarbejder medarbejder){
        medarbejderID.remove(medarbejder);
    }

    public ArrayList<String> getMedarbejderID() {
        return medarbejderID;
    }

    public void setMedarbejderID(ArrayList<Medarbejder> medarbejderID) {

        for(int i = 0; i< medarbejderID.size(); i++){
            this.medarbejderID.add(medarbejderID.get(i).getMedarbejderID());
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
