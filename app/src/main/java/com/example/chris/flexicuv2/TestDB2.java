package com.example.chris.flexicuv2;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;

public class TestDB2 {

    public TestDB2(File dbPath) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbPath + "/database.db", null);

        db.execSQL("CREATE TABLE IF NOT EXISTS 'Virksomhed' (" +
                "'CVR' TEXT NOT NULL PRIMARY KEY," +
                "'Navn' TEXT NOT NULL," +
                "'Adresse' TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS 'Bruger' (" +
                "'Email' TEXT NOT NULL PRIMARY KEY," +
                "'CVR' TEXT NOT NULL," +
                "'Fornavn' TEXT," +
                "'Tlfnr' TEXT," +
                "'Titel' TEXT," +
                "'Admin' INTEGER," + //0 = ikke admin, 1 = admin
                "CONSTRAINT fk_virksomhed " +
                "FOREIGN KEY('CVR')" +
                " REFERENCES 'Virksomhed'('CVR'))");

        db.execSQL("CREATE TABLE IF NOT EXISTS 'Medarbejder' (" +
                "'MedarbejderID' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "'CVR' TEXT NOT NULL," +
                "'Navn' TEXT NOT NULL," +
                "'HjemmeAdresse' TEXT," +
                "'HjemmePostnr' TEXT," +
                "'ArbejdsAdresse' TEXT," +
                "'ArbejdsPostnr' TEXT," +
                "'Månedsløn' TEXT," +
                "'Tlfnr' TEXT," +
                "CONSTRAINT fk_virksomhed " +
                "FOREIGN KEY('CVR')" +
                " REFERENCES 'Virksomhed'('CVR'))");

        db.execSQL("CREATE TABLE IF NOT EXISTS 'Aftale' (" +
                "'AftaleID' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "'UdlejerID' TEXT," +
                "'LejerID' TEXT," +
                "'MedarbejderID' INTEGER," +
                "'HelePeriode' INTEGER," + //0 = Skal lejes hele perioden, 1 = kan lejes i dele af perioden
                "'MinDage' INTEGER," +

                "CONSTRAINT fk_Udlejer_Virksomhed " +
                    "FOREIGN KEY('UdlejerID')" +
                    " REFERENCES 'Virksomhed'('CVR'), " +

                "CONSTRAINT fk_Lejer_Virksomhed " +
                    "FOREIGN KEY('LejerID')" +
                    " REFERENCES 'Virksomhed'('CVR')," +

                "CONSTRAINT fk_Medarbejder_Virksomhed " +
                    "FOREIGN KEY('MedarbejderID')" +
                    "REFERENCES Medarbejder_akt('MedarbejderID'))");


        db.execSQL("CREATE TABLE IF NOT EXISTS 'AftaleIndhold' (" +
                "'ID' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "'AftaleID' INTEGER," +
                "'Status' INTEGER," +
                "'StartDato' TEXT," +
                "'EndDato' TEXT," +
                "'Pris' INTEGER," +
                "'Kommentar' TEXT," +

                "CONSTRAINT fk_Aftale " +
                "FOREIGN KEY('AftaleID')" +
                " REFERENCES 'Aftale'('AftaleID'))");
    }

}
