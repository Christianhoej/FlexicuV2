package com.example.chris.flexicuv2.model;

import android.app.Activity;

import java.util.ArrayList;

public class Singleton extends Activity {

    private static Singleton singleInstance;
    public static ArrayList<Medarbejder> medarbejdere;
    private static ArrayList<Medarbejder> lånteMedarbejdere = new ArrayList<Medarbejder>();
    public static Bruger bruger;
    public static Medarbejder midlertidigMedarbejder;
    public static String userID;
    public static Bruger midlertidigBruger;
    public static ArrayList<Medarbejder> medarbejdereTilUdlejning;


    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleInstance == null){
            singleInstance = new Singleton();
            medarbejdere = new ArrayList<>();
            medarbejdereTilUdlejning = new ArrayList<>();
            bruger = new Bruger();
        }
        return singleInstance;
    }

    public static ArrayList<Medarbejder> getMedarbejdereTilUdlejning() {
        return medarbejdereTilUdlejning;
    }

    public static void setMedarbejdereTilUdlejning(ArrayList<Medarbejder> medarbejdereTilUdlejning) {
        Singleton.medarbejdereTilUdlejning = medarbejdereTilUdlejning;
    }

    public static void addMedarbejderTilUdlejning(Medarbejder medarbejderUdlejning){
        Singleton.medarbejdereTilUdlejning.add(medarbejderUdlejning);
    }

    public static void removeMedarbejderTilUdlejning(Medarbejder medarbejderUdlejning){
        Singleton.medarbejdereTilUdlejning.remove(medarbejderUdlejning);
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

    public static Bruger getBruger() {
        return bruger;
    }

    public static void setBruger(Bruger bruger) {
        Singleton.bruger = bruger;
    }

}
