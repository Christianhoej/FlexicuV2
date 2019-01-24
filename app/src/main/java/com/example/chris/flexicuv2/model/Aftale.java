package com.example.chris.flexicuv2.model;

import java.util.ArrayList;

public class Aftale implements Comparable<Aftale>{


    private boolean aktiv;
    private String AftaleID;
    private Bruger udlejer;
    private Medarbejder medarbejder;
    private String startDato;
    private String slutDato;
    private String timePris;
    private String kommentar;
    private boolean egetVærktøj;
    private ArrayList<Forhandling> forhandlinger = new ArrayList<>();
    private long timestamp;
    private double score; // skal anvendes til at filtrere med

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public String getAftaleID() {
        return AftaleID;
    }

    public void setAftaleID(String aftaleID) {
        AftaleID = aftaleID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Bruger getUdlejer() {
        return udlejer;
    }

    public void setUdlejer(Bruger udlejer) {
        this.udlejer = udlejer;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }

    public String getStartDato() {
        return startDato;
    }

    public void setStartDato(String startDato) {
        this.startDato = startDato;
    }

    public String getSlutDato() {
        return slutDato;
    }

    public void setSlutDato(String slutDato) {
        this.slutDato = slutDato;
    }

    public String getTimePris() {
        return timePris;
    }

    public void setTimePris(String timePris) {
        this.timePris = timePris;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public boolean isEgetVærktøj() {
        return egetVærktøj;
    }

    public void setEgetVærktøj(boolean egetVærktøj) {
        this.egetVærktøj = egetVærktøj;
    }

    public ArrayList<Forhandling> getForhandlinger() {
        return forhandlinger;
    }

    public void setForhandlinger(ArrayList<Forhandling> forhandlinger) {
        this.forhandlinger = forhandlinger;
    }

    public void addForhandlinger(Forhandling forhandlinger) {
        this.forhandlinger.add(forhandlinger);
    }

    public void removeForhandlinger(Forhandling forhandlinger) {
        this.forhandlinger.add(forhandlinger);
    }

    @Override
    public int compareTo(Aftale o) {
        System.out.println();
        System.out.println(o.getMedarbejder().toString() + "Dette er o" + o.getScore());
        System.out.println(getMedarbejder().toString() + "Dette er klassen " + score);
        System.out.println();
        if (o.getScore()>score)
            return 1;
        else if(o.getScore()<score)
            return -1;

        return 0;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
