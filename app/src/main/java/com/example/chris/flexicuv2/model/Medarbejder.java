package com.example.chris.flexicuv2.model;

import java.util.Calendar;

public class Medarbejder implements Comparable<Medarbejder>{
    private String medarbejderID;
    private String navn;
    private String hjemmeAdresse;
    private String hjemmePostnr;
    private String ardejdsAdresse;
    private String arbejdsPostnr;
    private int loen;
    private String tlfnr;
    private String arbejdsomraade;
    private String virksomhedsID;
    private String køn;
    private int fødselsår;
    private String email;
    private String vejnavn;
    private String nummer;
    private String postnr;
    private String latitude;
    private String longitude;

    public String getVejnavn() {
        return vejnavn;
    }

    public void setVejnavn(String vejnavn) {
        this.vejnavn = vejnavn;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

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

    public String getArbejdsomraade() {
        return arbejdsomraade;
    }

    public void setArbejdsomraade(String arbejdsomraade) {
        this.arbejdsomraade = arbejdsomraade;
    }

    @Override
    public int compareTo(Medarbejder o) {
        return navn.compareToIgnoreCase(o.getNavn());
    }

    public String getVirksomhedsID() {
        return virksomhedsID;
    }

    public void setVirksomhedsID(String virksomhedsID) {
        this.virksomhedsID = virksomhedsID;
    }

    public String getKøn() {
        return køn;
    }

    public void setKøn(String køn) {
        this.køn = køn;
    }

    public int getFødselsår() {
        return fødselsår;
    }

    public void setFødselsår(int fødselsår) {
        this.fødselsår = fødselsår;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        int alder = Calendar.getInstance().get(Calendar.YEAR)- fødselsår;
        if (alder != 0)
        return  navn + ", " + alder + " år, " + arbejdsomraade;
        else return navn;
    }
}
