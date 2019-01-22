package com.example.chris.flexicuv2.model;

import android.app.Activity;

import com.example.chris.flexicuv2.startskærm.lej.Filter;

import java.util.ArrayList;

public class Singleton extends Activity {

    private static Singleton singleInstance;
    public static ArrayList<Medarbejder> medarbejdere;
    public static Bruger bruger;
    public static String userID;
    public static ArrayList<Aftale> mineUdlejAftalerMedForhandling;
    public static ArrayList<Aftale> mineLejAftalerMedForhandling;
    public static ArrayList<Aftale> mineMedarbejderUdbud;
    public static ArrayList<Aftale> mineAfsluttedeAftaler;
  //  public static ArrayList<Forhandling> mineLejForhandlinger;
    public static ArrayList<Aftale> andresMedarbejderUdbud;
    public static Medarbejder midlertidigMedarbejder;
    public static Bruger midlertidigBruger;
    public static Forhandling midlertidigForhandling;
    public static Aftale midlertidigAftale;
    public static Filter søgeFiltrering;



    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleInstance == null){
            singleInstance = new Singleton();
            medarbejdere = new ArrayList<>();
            andresMedarbejderUdbud = new ArrayList<>();
            bruger = new Bruger();
        //    mineMedarbejderUdbud = new ArrayList<>();
            mineMedarbejderUdbud = new ArrayList<>();
        //    mineLejForhandlinger = new ArrayList<>();
            mineUdlejAftalerMedForhandling = new ArrayList<>();
            mineLejAftalerMedForhandling = new ArrayList<>();
            mineAfsluttedeAftaler = new ArrayList<>();
        }
        return singleInstance;
    }

    public static ArrayList<Aftale> getMineAfsluttedeAftaler() {
        return mineAfsluttedeAftaler;
    }

    public static void setMineAfsluttedeAftaler(ArrayList<Aftale> mineAfsluttedeAftaler) {
        Singleton.mineAfsluttedeAftaler = mineAfsluttedeAftaler;
    }

    public static ArrayList<Aftale> getMineUdlejAftalerMedForhandling() {
        return mineUdlejAftalerMedForhandling;
    }

    public static void setMineUdlejAftalerMedForhandling(ArrayList<Aftale> mineUdlejAftalerMedForhandling) {
        Singleton.mineUdlejAftalerMedForhandling = mineUdlejAftalerMedForhandling;
    }

    public static void addMineUdejAftalerMedForhandling(Aftale mineUdlejAftalerMedForhandling){
        Singleton.mineUdlejAftalerMedForhandling.add(mineUdlejAftalerMedForhandling);
    }

    public static void removeMineUdlejAftalerMedForhandling(Aftale mineUdlejAftalerMedForhandling){
        Singleton.mineUdlejAftalerMedForhandling.remove(mineUdlejAftalerMedForhandling);
    }

    public static ArrayList<Aftale> getMineLejAftalerMedForhandling() {

        return mineLejAftalerMedForhandling;
    }

    public static void setMineLejAftalerMedForhandling(ArrayList<Aftale> mineLejAftalerMedForhandling) {
        Singleton.mineLejAftalerMedForhandling = mineLejAftalerMedForhandling;
    }

    public static void addMineLejAftalerMedForhandling(Aftale mineLejAftalerMedForhandling){
        Singleton.mineLejAftalerMedForhandling.add(mineLejAftalerMedForhandling);
    }

    public static void removeMineLejAftalerMedForhandling(Aftale mineLejAftalerMedForhandling){
        Singleton.mineLejAftalerMedForhandling.remove(mineLejAftalerMedForhandling);
    }

   /* public static ArrayList<Forhandling> getMineLejForhandlinger() {
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
    }*/

    public static ArrayList<Aftale> getMineMedarbejderUdbud() {
        return mineMedarbejderUdbud;
    }

    public static void setMineMedarbejderUdbud(ArrayList<Aftale> mineMedarbejderUdbud) {
        Singleton.mineMedarbejderUdbud = mineMedarbejderUdbud;
    }

    public static void addMineMedarbejderUdbud(Aftale mineForhandlinger){
        Singleton.mineMedarbejderUdbud.add(mineForhandlinger);
    }

    public static void removeMineMedarbejderUdbud(Aftale mineForhandlinger){
        Singleton.mineMedarbejderUdbud.add(mineForhandlinger);
    }

/*
    public static ArrayList<Forhandling> getMineLedigeMedarbejdere() {
        return mineMedarbejderUdbud;
    }

    public static void setMineLedigeMedarbejdere(ArrayList<Forhandling> ledigeMedarbejder) {
        Singleton.mineMedarbejderUdbud = ledigeMedarbejder;
    }

    public static void addLedigeMedarbejder(Forhandling forhandling){
        Singleton.mineMedarbejderUdbud.add(forhandling);
    }

    public static void removeLedigeMedarbejder(Forhandling forhandling){
        Singleton.mineMedarbejderUdbud.add(forhandling);
    }
*/
    public static ArrayList<Aftale> getAndresMedarbejderUdbud() {
        return andresMedarbejderUdbud;
    }

    public static void setAndresMedarbejderUdbud(ArrayList<Aftale> andresMedarbejderUdbud) {
        Singleton.andresMedarbejderUdbud = andresMedarbejderUdbud;
    }

    public static void addAndresMedarbejderUdbud(Aftale andresMedarbejderUdbud){
        Singleton.andresMedarbejderUdbud.add(andresMedarbejderUdbud);
    }

    public static void removeAndresMedarbejderUdbud(Aftale andresMedarbejderUdbud){
        Singleton.andresMedarbejderUdbud.remove(andresMedarbejderUdbud);
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
