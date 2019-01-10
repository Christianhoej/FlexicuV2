package com.example.chris.flexicuv2;

import android.app.Application;
import android.provider.ContactsContract;

import com.example.chris.flexicuv2.model.Bruger;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBManager {

    private Bruger bruger;
    private Singleton singleton;
    private Medarbejder med1 = new Medarbejder();

    public DBManager() {
        singleton = Singleton.getInstance();
        bruger = new Bruger();
        bruger.setVirksomhedCVR("11223344æljlkj");
        bruger.setVirksomhedsnavn("Flexicu");
        bruger.setAdresse("Anker Engelundsvej 1");
        bruger.setPostnr("2800");
        singleton.setBruger(bruger);
        med1.setNavn("Oliver");
        med1.setLoen(100);
        med1.setArbejdsomraade("Smed");

        singleton.addMedarbejder(med1);
        createBruger(bruger);
        createMedarbejder(med1);
    }

    public void createBruger(Bruger bruger) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String virkKey = ref.child("bruger").push().getKey();

        bruger.setBrugerID(virkKey);
        ref.child("bruger").child(virkKey).setValue(bruger);
    }

    public void createMedarbejder(Medarbejder medarbejder) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String medKey = ref.child("medarbejder").push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child("medarbejder").child(medKey).setValue(medarbejder);

        ref.child("bruger").child(singleton.getBruger().getBrugerID()).child("medarbejdere").setValue(medKey);
    }

    public void updateBruger(Bruger bruger) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child("bruger").child(bruger.getBrugerID());
        ref.setValue(bruger);


    }


    public void updateMedarbejder(Medarbejder medarbejder) {

    }
}