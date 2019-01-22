package com.example.chris.flexicuv2.model;

import android.app.Activity;

import com.example.chris.flexicuv2.startskærm.lej.Filter;

import java.util.ArrayList;

public class Singleton extends Activity {

    private static Singleton singleInstance;
    public static ArrayList<Medarbejder> medarbejdere;
    private static ArrayList<Medarbejder> lånteMedarbejdere = new ArrayList<>();
    public static Bruger bruger;

    public static String userID;
    public static ArrayList<ArrayList<Forhandling>> mineUdlejAftalerMedForhandling;
    public static ArrayList<ArrayList<Forhandling>> mineLejAftalerMedForhandling;
    public static ArrayList<Forhandling> mineUdlejForhandlinger;
    public static ArrayList<Forhandling> mineLejForhandlinger;
    public static ArrayList<Forhandling> mineLedigeMedarbejdere;
    public static ArrayList<Forhandling> medarbejdereTilUdlejning;
    public static Medarbejder midlertidigMedarbejder;
    public static Bruger midlertidigBruger;
    public static Forhandling midlertidigForhandling;
    public static Filter søgeFiltrering;



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

    public static ArrayList<ArrayList<Forhandling>> getMineUdlejAftalerMedForhandling() {
        return mineUdlejAftalerMedForhandling;
    }

    public static void setMineUdlejAftalerMedForhandling(ArrayList<ArrayList<Forhandling>> mineUdlejAftalerMedForhandling) {
        Singleton.mineUdlejAftalerMedForhandling = mineUdlejAftalerMedForhandling;
    }

    public static void addMineUdejAftalerMedForhandling(ArrayList<Forhandling> mineUdlejAftalerMedForhandling){
        Singleton.mineUdlejAftalerMedForhandling.add(mineUdlejAftalerMedForhandling);
    }

    public static void removeMineUdlejAftalerMedForhandling(ArrayList<Forhandling> mineUdlejAftalerMedForhandling){
        Singleton.mineUdlejAftalerMedForhandling.remove(mineUdlejAftalerMedForhandling);
    }

    public static void addUdlejForhandlingTilAftale(Forhandling forhandling, int index){
        Singleton.mineUdlejAftalerMedForhandling.get(index).add(forhandling);
    }

    public static ArrayList<ArrayList<Forhandling>> getMineLejAftalerMedForhandling() {
        return mineLejAftalerMedForhandling;
    }

    public static void setMineLejAftalerMedForhandling(ArrayList<ArrayList<Forhandling>> mineLejAftalerMedForhandling) {
        Singleton.mineLejAftalerMedForhandling = mineLejAftalerMedForhandling;
    }

    public static void addMineLejAftalerMedForhandling(ArrayList<Forhandling> mineLejAftalerMedForhandling){
        Singleton.mineLejAftalerMedForhandling.add(mineLejAftalerMedForhandling);

    }

    public static void removeMineLejAftalerMedForhandling(ArrayList<Forhandling> mineLejAftalerMedForhandling){
        Singleton.mineLejAftalerMedForhandling.remove(mineLejAftalerMedForhandling);
    }

    public static void addLejForhandlingTilAftale(Forhandling forhandling, int index){
        Singleton.mineLejAftalerMedForhandling.get(index).add(forhandling);
    }

    public static ArrayList<Forhandling> getMineLejForhandlinger() {
        return mineLejForhandlinger;
    }

    public static void setMineLejForhandlinger(ArrayList<Forhandling> mineLejForhandlinger) {
        Singleton.mineLejForhandlinger = mineLejForhandlinger;
    }

    public static void addMineLejForhandlinger(Forhandling mineLejForhandlinger){
        Singleton.mineLejForhandlinger.add(mineLejForhandlinger);
    }

    public static void removeMineLejForhandlinger(Forhandling mineLejForhandlinger){
        Singleton.mineLejForhandlinger.add(mineLejForhandlinger);
    }

    public static ArrayList<Forhandling> getMineUdlejForhandlinger() {
        return mineUdlejForhandlinger;
    }

    public static void setMineUdlejForhandlinger(ArrayList<Forhandling> mineUdlejForhandlinger) {
        Singleton.mineUdlejForhandlinger = mineUdlejForhandlinger;
    }

    public static void addMineUdlejForhandlinger(Forhandling mineForhandlinger){
        Singleton.mineUdlejForhandlinger.add(mineForhandlinger);
    }

    public static void removeMineUdlejForhandlinger(Forhandling mineForhandlinger){
        Singleton.mineUdlejForhandlinger.add(mineForhandlinger);
    }


    public static ArrayList<Forhandling> getMineLedigeMedarbejdere() {
        return mineLedigeMedarbejdere;
    }

    public static void setMineLedigeMedarbejdere(ArrayList<Forhandling> ledigeMedarbejder) {
        Singleton.mineLedigeMedarbejdere = ledigeMedarbejder;
    }

    public static void addLedigeMedarbejder(Forhandling forhandling){
        Singleton.mineLedigeMedarbejdere.add(forhandling);
    }

    public static void removeLedigeMedarbejder(Forhandling forhandling){
        Singleton.mineLedigeMedarbejdere.add(forhandling);
    }

    public static ArrayList<Forhandling> getMedarbejdereTilUdlejning() {
        return medarbejdereTilUdlejning;
    }

    public static void setMedarbejdereTilUdlejning(ArrayList<Forhandling> medarbejdereTilUdlejning) {
        Singleton.medarbejdereTilUdlejning = medarbejdereTilUdlejning;
    }

    public static void addMedarbejderTilUdlejning(Forhandling medarbejderUdlejning){
        Singleton.medarbejdereTilUdlejning.add(medarbejderUdlejning);
    }

    public static void removeMedarbejderTilUdlejning(Forhandling medarbejderUdlejning){
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
