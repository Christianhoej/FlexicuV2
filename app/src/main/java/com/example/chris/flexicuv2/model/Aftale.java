package com.example.chris.flexicuv2.model;

public class Aftale implements Comparable<Aftale>{

    private String aftaleID;
    private String oprindeligUdlejID;
    private String forhandlingID;
    private Bruger udlejer;
    private Bruger lejer;
    private Medarbejder medarbejder;
    private int status;
    private String startDato;
    private String endDato;
    private String pris;
    private String kommentar;
    private boolean egetVærktøj;
    private Bruger sidstSendtAftale;
    private boolean aktiv;

    int filterScore;

    private int timepris;


    public String getForhandlingID() {
        return forhandlingID;
    }

    public void setForhandlingID(String forhandlingID) {
        this.forhandlingID = forhandlingID;

    }
    public int getTimepris() {
        return timepris;

    }
        public int getTimepris () {
            return timepris;
        }


    public void setTimepris(int timepris) {
        this.timepris = timepris;
    }

    public String getOprindeligUdlejID() {
        return oprindeligUdlejID;
    }

    public void setOprindeligUdlejID(String oprindeligUdlejID) {
        this.oprindeligUdlejID = oprindeligUdlejID;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public boolean isEgetVærktøj() {
        return egetVærktøj;
    }

    public void setEgetVærktøj(boolean egetVærktøj) {
        this.egetVærktøj = egetVærktøj;
    }

    public String getAftaleID() {
        return aftaleID;
    }

    public void setAftaleID(String aftaleID) {
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

    public String getPris() {
        return pris;
    }

    public void setPris(String pris) {
        this.pris = pris;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public Bruger getSidstSendtAftale() {
        return sidstSendtAftale;
    }

    public void setSidstSendtAftale(Bruger sidstSendtAftale) {
        this.sidstSendtAftale = sidstSendtAftale;
    }

    @Override
    public int compareTo(Aftale o) {
        if(this.filterScore > o.filterScore)
            return 1;
            else if(o.filterScore== this.filterScore)
        return 0;
            else
                return -1;

    }
}