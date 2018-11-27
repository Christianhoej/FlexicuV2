package com.example.chris.flexicuv2.model;

public class Bruger {
    private String email;
    private String navn;
    private String tlfnr;
    private String kodeord;
    private boolean admin;
    private String titel;
    private String brugerKey;


    public String getBrugerKey() {
        return brugerKey;
    }

    public void setBrugerKey(String brugerKey) {
        this.brugerKey = brugerKey;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTlfnr() {
        return tlfnr;
    }

    public void setTlfnr(String tlfnr) {
        this.tlfnr = tlfnr;
    }

    public String getKodeord() {
        return kodeord;
    }

    public void setKodeord(String kodeord) {
        this.kodeord = kodeord;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}
