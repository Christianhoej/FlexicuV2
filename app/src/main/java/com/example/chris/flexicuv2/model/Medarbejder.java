package com.example.chris.flexicuv2.model;

public class Medarbejder {
    private String medarbejderID;
    private String navn;
    private String hjemmeAdresse;
    private String hjemmePostnr;
    private String ardejdsAdresse;
    private String arbejdsPostnr;
    private int loen;
    private String tlfnr;

    public String getMedarbejderID() {
        return medarbejderID;
    }

    public void setMedarbejderID(String medarbejderID) {
        this.medarbejderID = medarbejderID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getHjemmeAdresse() {
        return hjemmeAdresse;
    }

    public void setHjemmeAdresse(String hjemmeAdresse) {
        this.hjemmeAdresse = hjemmeAdresse;
    }

    public String getHjemmePostnr() {
        return hjemmePostnr;
    }

    public void setHjemmePostnr(String hjemmePostnr) {
        this.hjemmePostnr = hjemmePostnr;
    }

    public String getArdejdsAdresse() {
        return ardejdsAdresse;
    }

    public void setArdejdsAdresse(String ardejdsAdresse) {
        this.ardejdsAdresse = ardejdsAdresse;
    }

    public String getArbejdsPostnr() {
        return arbejdsPostnr;
    }

    public void setArbejdsPostnr(String arbejdsPostnr) {
        this.arbejdsPostnr = arbejdsPostnr;
    }

    public int getLoen() {
        return loen;
    }

    public void setLoen(int loen) {
        this.loen = loen;
    }

    public String getTlfnr() {
        return tlfnr;
    }

    public void setTlfnr(String tlfnr) {
        this.tlfnr = tlfnr;
    }
}
