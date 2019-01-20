package com.example.chris.flexicuv2.model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Singleton extends Activity {

    private static Singleton singleInstance;
    public static ArrayList<Medarbejder> medarbejdere;
    private static ArrayList<Medarbejder> lånteMedarbejdere = new ArrayList<Medarbejder>();
    public static Bruger bruger;
    public static Medarbejder midlertidigMedarbejder;
    public static String userID;
    public static Bruger midlertidigBruger;
    public static ArrayList<Aftale> medarbejdereTilUdlejning;
    public static ArrayList<Aftale> ledigeMedarbejder;
    public static Aftale midlertidigAftale;


    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleInstance == null){
            singleInstance = new Singleton();
            medarbejdere = new ArrayList<>();
            medarbejdereTilUdlejning = new ArrayList<>();
            bruger = new Bruger();
            ledigeMedarbejder = new ArrayList<>();
        }
        return singleInstance;
    }


    public static ArrayList<Aftale> getLedigeMedarbejder() {
        return ledigeMedarbejder;
    }

    public static void setLedigeMedarbejder(ArrayList<Aftale> ledigeMedarbejder) {
        Singleton.ledigeMedarbejder = ledigeMedarbejder;
    }

    public static void addLedigeMedarbejder(Aftale aftale){
        Singleton.ledigeMedarbejder.add(aftale);
    }

    public static void removeLedigeMedarbejder(Aftale aftale){
        Singleton.ledigeMedarbejder.add(aftale);
    }

    public static ArrayList<Aftale> getMedarbejdereTilUdlejning() {
        return medarbejdereTilUdlejning;
    }

    public static void setMedarbejdereTilUdlejning(ArrayList<Aftale> medarbejdereTilUdlejning) {
        Singleton.medarbejdereTilUdlejning = medarbejdereTilUdlejning;
    }

    public static void addMedarbejderTilUdlejning(Aftale medarbejderUdlejning){
        Singleton.medarbejdereTilUdlejning.add(medarbejderUdlejning);
    }

    public static void removeMedarbejderTilUdlejning(Aftale medarbejderUdlejning){
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
