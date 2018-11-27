package com.example.chris.flexicuv2.model;

import java.util.ArrayList;

public class Aftale {

    private int aftaleID;
    private boolean helePeriode;
    private int minDage;
    private Virksomhed udlejer;
    private Virksomhed lejer;
    private Medarbejder medarbejder;
    private ArrayList<AftaleIndhold> aftaleIndhold = new ArrayList<AftaleIndhold>();

    public int getAftaleID() {
        return aftaleID;
    }

    public void setAftaleID(int aftaleID) {
        this.aftaleID = aftaleID;
    }

    public boolean isHelePeriode() {
        return helePeriode;
    }

    public void setHelePeriode(boolean helePeriode) {
        this.helePeriode = helePeriode;
    }

    public int getMinDage() {
        return minDage;
    }

    public void setMinDage(int minDage) {
        this.minDage = minDage;
    }

    public Virksomhed getUdlejer() {
        return udlejer;
    }

    public void setUdlejer(Virksomhed udlejer) {
        this.udlejer = udlejer;
    }

    public Virksomhed getLejer() {
        return lejer;
    }

    public void setLejer(Virksomhed lejer) {
        this.lejer = lejer;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }

    public void addAftaleIndhold(AftaleIndhold aftaleIndhold){
        this.aftaleIndhold.add(aftaleIndhold);
    }

}