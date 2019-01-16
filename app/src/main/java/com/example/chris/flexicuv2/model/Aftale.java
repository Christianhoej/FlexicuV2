package com.example.chris.flexicuv2.model;

import java.util.ArrayList;

public class Aftale {

    private int aftaleID;
    private Bruger udlejer;
    private Bruger lejer;
    private Medarbejder medarbejder;
    private int status;
    private String startDato;
    private String endDato;
    private int pris;
    private String kommentar;

    public boolean isEgetVærktøj() {
        return egetVærktøj;
    }

    public void setEgetVærktøj(boolean egetVærktøj) {
        this.egetVærktøj = egetVærktøj;
    }

    private boolean egetVærktøj;

    public int getAftaleID() {
        return aftaleID;
    }

    public void setAftaleID(int aftaleID) {
        this.aftaleID = aftaleID;
    }

    public Bruger getUdlejer() {
        return udlejer;
    }

    public void setUdlejer(Bruger udlejer) {
        this.udlejer = udlejer;
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
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartDato() {
        return startDato;
    }

    public void setStartDato(String startDato) {
        this.startDato = startDato;
    }

    public String getEndDato() {
        return endDato;
    }

    public void setEndDato(String endDato) {
        this.endDato = endDato;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

}