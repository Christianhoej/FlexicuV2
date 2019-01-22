package com.example.chris.flexicuv2.model;

import java.util.ArrayList;

public class Forhandling implements Comparable<Forhandling>{

    private String aftaleID;
//    private String oprindeligUdlejID;
    private String forhandlingID;
    //private Bruger udlejer;
    private Bruger lejer;
    private Medarbejder medarbejder;
    private boolean aktiv;
    private long timestamp;
    private Bruger sidstSendtAftale;
    int filterScore;
    private boolean aftaleIndgået = false;

    private String udlejerStartDato;
    private String udlejerSlutDato;
    private String udlejPris;
    private ArrayList<String> udlejKommentar;
    private boolean udlejEgetVærktøj;


    private String lejerStartDato;
    private String lejerSlutDato;
    private String lejPris;
    private ArrayList<String> lejKommentar;
    private boolean lejEgetVærktøj;



    //private int timepris;


    public String getForhandlingID() {
        return forhandlingID;
    }

    public void setForhandlingID(String forhandlingID) {
        this.forhandlingID = forhandlingID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public boolean isUdlejEgetVærktøj() {
        return udlejEgetVærktøj;
    }

    public void setUdlejEgetVærktøj(boolean udlejEgetVærktøj) {
        this.udlejEgetVærktøj = udlejEgetVærktøj;
    }

    public String getAftaleID() {
        return aftaleID;
    }

    public void setAftaleID(String aftaleID) {
        this.aftaleID = aftaleID;
    }

    public boolean isAftaleIndgået() {
        return aftaleIndgået;
    }

    public void setAftaleIndgået(boolean aftaleIndgået) {
        this.aftaleIndgået = aftaleIndgået;
    }

    public String getLejerStartDato() {
        return lejerStartDato;
    }

    public void setLejerStartDato(String lejerStartDato) {
        this.lejerStartDato = lejerStartDato;
    }

    public String getLejerSlutDato() {
        return lejerSlutDato;
    }

    public void setLejerSlutDato(String lejerSlutDato) {
        this.lejerSlutDato = lejerSlutDato;
    }

    public String getLejPris() {
        return lejPris;
    }

    public void setLejPris(String lejPris) {
        this.lejPris = lejPris;
    }

    public ArrayList<String> getLejKommentar() {
        return lejKommentar;
    }

    public void setLejKommentar(ArrayList<String> lejKommentar) {
        this.lejKommentar = lejKommentar;
    }

    public void addLejKommentar(String lejKommentar){
        this.lejKommentar.add(lejKommentar);
    }

    public void removeLejKommentar(String lejKommentar){
        this.lejKommentar.remove(lejKommentar);
    }

    public boolean isLejEgetVærktøj() {
        return lejEgetVærktøj;
    }

    public void setLejEgetVærktøj(boolean lejEgetVærktøj) {
        this.lejEgetVærktøj = lejEgetVærktøj;
    }

    public Bruger getLejer() {
        return lejer;
    }

    public void setLejer(Bruger lejer) {
        this.lejer = lejer;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }

    public String getUdlejerStartDato() {
        return udlejerStartDato;
    }

    public void setUdlejerStartDato(String udlejerStartDato) {
        this.udlejerStartDato = udlejerStartDato;
    }

    public String getUdlejerSlutDato() {
        return udlejerSlutDato;
    }

    public void setUdlejerSlutDato(String udlejerSlutDato) {
        this.udlejerSlutDato = udlejerSlutDato;
    }

    public String getUdlejPris() {
        return udlejPris;
    }

    public void setUdlejPris(String udlejPris) {
        this.udlejPris = udlejPris;
    }

    public ArrayList<String> getUdlejKommentar() {
        return udlejKommentar;
    }

    public void setUdlejKommentar(ArrayList<String> udlejKommentar) {
        this.udlejKommentar = udlejKommentar;
    }

    public void addUdlejKommentar(String udlejKommentar){
        this.udlejKommentar.add(udlejKommentar);
    }

    public void removeUdlejKommentar(String udlejKommentar){
        this.udlejKommentar.remove(udlejKommentar);
    }

    public Bruger getSidstSendtAftale() {
        return sidstSendtAftale;
    }

    public void setSidstSendtAftale(Bruger sidstSendtAftale) {
        this.sidstSendtAftale = sidstSendtAftale;
    }

    @Override
    public int compareTo(Forhandling o) {
        if(this.filterScore > o.filterScore)
            return 1;
            else if(o.filterScore== this.filterScore)
        return 0;
            else
                return -1;

    }
}