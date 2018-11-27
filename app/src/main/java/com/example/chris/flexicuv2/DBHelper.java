package com.example.chris.flexicuv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.constraint.ConstraintLayout;

public class DBHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "database.db";
    private static final int DB_VERSION = 1;

    private static DBHelper dbHelper;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

   /* public static synchronized DBHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }

        return dbHelper;
    }

    static final class VirksomhedTable implements BaseColumns {
        static final String VIRK_TABLE_NAME = "Virksomhed";


        static final String[] VIRK_COLS = new String[] {"CVR", "Navn", "Adresse", "Postnr"};

        static final String CREATE = "CREATE TABLE IF NOT EXISTS "+VIRK_TABLE_NAME + "(" +
                "Cvr TEXT PRIMARY KEY AUTOINCREMENT," +
                "Navn TEXT," +
                "Adresse TEXT," +
                "Postnr TEXT)";
    }

    static  final class BrugerTable implements BaseColumns{
        static final String BRUGER_TABLE_NAME = "Bruger";

        static final String[] BRUGER_COLS = new String[] {"Email", "CVR", "Fornavn", "Efternavn", "Tlfnr", "Titel", "Admin"};

        static final String CREATE = "CREATE TABLE IF NOT EXISTS "+BRUGER_TABLE_NAME + "(" +
                "Cvr TEXT PRIMARY KEY AUTOINCREMENT," +
                "Navn TEXT," +
                "Adresse TEXT," +
                "Postnr TEXT)";


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
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VirksomhedTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }*/
}
