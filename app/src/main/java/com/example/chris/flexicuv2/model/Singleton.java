package com.example.chris.flexicuv2.model;

import android.app.Activity;

import java.util.ArrayList;

public class Singleton extends Activity {

    private static Singleton singleInstance;
    public static ArrayList<Medarbejder> medarbejdere = new ArrayList<Medarbejder>();
    private static ArrayList<Medarbejder> lånteMedarbejdere = new ArrayList<Medarbejder>();
    public static Virksomhed virksomhed;

    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleInstance == null){
            singleInstance = new Singleton();
            medarbejdere = new ArrayList<>();
            virksomhed = new Virksomhed();
        }
        return singleInstance;
    }

    public static ArrayList<Medarbejder> getMedarbejdere() {
        return medarbejdere;
    }

    public static void setMedarbejdere(ArrayList<Medarbejder> medarbejdere) {
        Singleton.medarbejdere = medarbejdere;
    }

    public static void addMedarbejder(Medarbejder medarbejder) {
        Singleton.medarbejdere.add(medarbejder);
    }

    public static void removeMedarbejder(Medarbejder medarbejder) {
        Singleton.medarbejdere.remove(medarbejder);
    }

    public static Virksomhed getVirksomhed() {
        return virksomhed;
    }

    public static void setVirksomhed(Virksomhed virksomhed) {
        Singleton.virksomhed = virksomhed;
    }

}
