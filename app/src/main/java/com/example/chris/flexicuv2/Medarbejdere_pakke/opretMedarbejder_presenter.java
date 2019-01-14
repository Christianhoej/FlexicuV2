package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.util.Patterns;

import com.example.chris.flexicuv2.DBManager;

/**
 * @Author
 */
public class opretMedarbejder_presenter {

    private UpdateOpretMedarbejderAkt updateOpretMedarbejderAkt;
    private final static opretMedarbejder_presenter INSTANCE = new opretMedarbejder_presenter();

    private DBManager dbManager;



    public void setUpdateOpretMedarbejderAkt(UpdateOpretMedarbejderAkt updateOpretMedarbejderAkt) {
        this.updateOpretMedarbejderAkt = updateOpretMedarbejderAkt;
    }



    interface UpdateOpretMedarbejderAkt{
//TODO metode der opdaterer recyclerview
    }







}
