package com.example.chris.flexicuv2.model;

import android.app.Activity;

import java.util.ArrayList;

public class Singleton extends Activity {

    private static Singleton singleInstance;
    public static ArrayList<Medarbejder> medarbejdere;
    private static ArrayList<Medarbejder> lånteMedarbejdere = new ArrayList<Medarbejder>();
    public static Bruger bruger;

    public static String userID;
    public static ArrayList<Aftale> mineUdlejForhandlinger;
    public static ArrayList<Aftale> mineLejForhandlinger;
    public static ArrayList<Aftale> mineLedigeMedarbejdere;
    public static ArrayList<Aftale> medarbejdereTilUdlejning;
    public static Medarbejder midlertidigMedarbejder;
    public static Bruger midlertidigBruger;
    public static Aftale midlertidigAftale;



    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleInstance == null){
            singleInstance = new Singleton();
            medarbejdere = new ArrayList<>();
            medarbejdereTilUdlejning = new ArrayList<>();
            bruger = new Bruger();
            mineLedigeMedarbejdere = new ArrayList<>();
            mineUdlejForhandlinger = new ArrayList<>();
            mineLejForhandlinger = new ArrayList<>();
        }
        return singleInstance;
    }

    public static ArrayList<Aftale> getMineLejForhandlinger() {
        return mineLejForhandlinger;
    }

    public static void setMineLejForhandlinger(ArrayList<Aftale> mineLejForhandlinger) {
        Singleton.mineLejForhandlinger = mineLejForhandlinger;
    }

    public static void addMineLejForhandlinger(Aftale mineLejForhandlinger){
        singleInstance.mineLejForhandlinger.add(mineLejForhandlinger);
    }

    public static void removeMineLejForhandlinger(Aftale mineLejForhandlinger){
        singleInstance.mineLejForhandlinger.add(mineLejForhandlinger);
    }

    public static ArrayList<Aftale> getMineUdlejForhandlinger() {
        return mineUdlejForhandlinger;
    }

    public static void setMineUdlejForhandlinger(ArrayList<Aftale> mineUdlejForhandlinger) {
        Singleton.mineUdlejForhandlinger = mineUdlejForhandlinger;
    }

    public static void addMineUdlejForhandlinger(Aftale mineForhandlinger){
        singleInstance.mineUdlejForhandlinger.add(mineForhandlinger);
    }

    public static void removeMineUdlejForhandlinger(Aftale mineForhandlinger){
        singleInstance.mineUdlejForhandlinger.add(mineForhandlinger);
    }


    public static ArrayList<Aftale> getMineLedigeMedarbejdere() {
        return mineLedigeMedarbejdere;
    }

    public static void setMineLedigeMedarbejdere(ArrayList<Aftale> ledigeMedarbejder) {
        Singleton.mineLedigeMedarbejdere = ledigeMedarbejder;
    }

    public static void addLedigeMedarbejder(Aftale aftale){
        Singleton.mineLedigeMedarbejdere.add(aftale);
    }

    public static void removeLedigeMedarbejder(Aftale aftale){
        Singleton.mineLedigeMedarbejdere.add(aftale);
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
