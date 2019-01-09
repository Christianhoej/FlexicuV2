package com.example.chris.flexicuv2;

import android.app.Application;
import android.provider.ContactsContract;

import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.model.Virksomhed;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBManager {

    private Virksomhed virk;
    private Singleton singleton;
    private Medarbejder med1 = new Medarbejder();

    public DBManager() {
        singleton = Singleton.getInstance();
        virk = new Virksomhed();
        virk.setVirksomhedCVR("11223344æljlkj");
        virk.setVirksomhedsnavn("Flexicu");
        virk.setAdresse("Anker Engelundsvej 1");
        virk.setPostnr("2800");
        singleton.setVirksomhed(virk);
        med1.setNavn("Oliver");
        med1.setLoen(100);
        med1.setArbejdsomraade("Smed");

        singleton.addMedarbejder(med1);
        createVirksomhed(virk);
        createMedarbejder(med1);
    }

    public void createVirksomhed(Virksomhed virksomhed) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String virkKey = ref.child("virksomhed").push().getKey();

        virksomhed.setVirksomhedID(virkKey);
        ref.child("virksomhed").child(virkKey).setValue(virksomhed);
    }

    public void createMedarbejder(Medarbejder medarbejder) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String medKey = ref.child("medarbejder").push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child("medarbejder").child(medKey).setValue(medarbejder);

        ref.child("virksomhed").child(singleton.getVirksomhed().getVirksomhedID()).child("medarbejdere").setValue(medKey);
    }

    public void updateVirksomhed(Virksomhed virksomhed) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child("virksomhed").child(virksomhed.getVirksomhedID());
        ref.setValue(virksomhed);


    }


    public void updateMedarbejder(Medarbejder medarbejder) {

    }
}