package com.example.chris.flexicuv2.model;

import android.os.AsyncTask;

import java.util.ArrayList;

public class DataSingleton {

    private static final DataSingleton INSTANCE = new DataSingleton();
    private ArrayList<Medarbejder> medarbejder = new ArrayList<Medarbejder>();
    private ArrayList<Medarbejder> lånteMedarbejdere = new ArrayList<Medarbejder>();
    private Virksomhed virksomhed;

    private DataSingleton(){
        //hent virksomhedens CVR;
        //hent medarbejder fra databasen;
        //hent lånte medarbejdere;

    };

    public static DataSingleton getInstance(){
        return INSTANCE;
    }

}
