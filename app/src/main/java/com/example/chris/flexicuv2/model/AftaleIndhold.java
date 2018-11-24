package com.example.chris.flexicuv2.model;

public class AftaleIndhold {

    private int status;
    private String startDato;
    private String endDato;
    private int pris;
    private String kommentar;

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
