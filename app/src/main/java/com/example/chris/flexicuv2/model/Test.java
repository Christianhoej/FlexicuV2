package com.example.chris.flexicuv2.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Test {

    private final DatabaseReference mDatabase;
    Virksomhed virk = new Virksomhed();
    Bruger bruger1 = new Bruger();
    Bruger bruger2 = new Bruger();
    Medarbejder med1 = new Medarbejder();
    Medarbejder med2 = new Medarbejder();
    Medarbejder med3 = new Medarbejder();

    public Test(){

        virk.setVirksomhedCVR("11223344");
        virk.setVirksomhedsnavn("Flexicu");
        virk.setAdresse("Anker Engelundsvej 1");
        virk.setPostnr("2800");
        bruger1.setNavn("Janus");
        bruger1.setEmail("janus@janus.com");
        bruger1.setTitel("Taber");
        bruger1.setTlfnr("34543265");
        bruger1.setAdmin(false);
        bruger2.setNavn("Gunn");
        bruger2.setEmail("gunn@gunn.com");
        bruger2.setTitel("CEO");
        bruger2.setTlfnr("93912344");
        bruger2.setAdmin(true);
        med1.setNavn("Oliver");
        med1.setLoen(100);
        med2.setNavn("Adam");
        med2.setLoen(115);
        med3.setNavn("Ahad");
        med3.setLoen(100);
        virk.addBruger(bruger1);
        virk.addBruger(bruger2);
        virk.addMedarbejdere(med1);
        virk.addMedarbejdere(med2);
        virk.addMedarbejdere(med3);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("virksomhed").child(virk.getVirksomhedCVR()).setValue(virk);


        DatabaseReference dbBruger = FirebaseDatabase.getInstance().getReference();

        int bruger1index = virk.getBrugere().indexOf(bruger1);
        int bruger2index = virk.getBrugere().indexOf(bruger2);


        String key1 = dbBruger.child("bruger").push().getKey();
        dbBruger.child("bruger").child(key1).setValue(bruger1);
        dbBruger.child("bruger").child(key1).child("virksomhed").child(virk.getVirksomhedCVR()).setValue(true);

        String key2 = dbBruger.child("bruger").push().getKey();
        dbBruger.child("bruger").child(key2).setValue(bruger2);
        dbBruger.child("bruger").child(key2).child("virksomhed").child(virk.getVirksomhedCVR()).setValue(true);


        String key3 = dbBruger.child("medarbejder").push().getKey();
        dbBruger.child("medarbejder").child(key3).setValue(med1);
        dbBruger.child("medarbejder").child(key3).child("virksomhed").child(virk.getVirksomhedCVR()).setValue(true);

        String key4 = dbBruger.child("medarbejder").push().getKey();
        dbBruger.child("medarbejder").child(key4).setValue(med2);
        dbBruger.child("medarbejder").child(key4).child("virksomhed").child(virk.getVirksomhedCVR()).setValue(true);

        String key5 = dbBruger.child("medarbejder").push().getKey();
        dbBruger.child("medarbejder").child(key5).setValue(med3);
        dbBruger.child("medarbejder").child(key5).child("virksomhed").child(virk.getVirksomhedCVR()).setValue(true);
        bruger1.setBrugerKey("Key = " + dbBruger.getKey());
        System.out.println("index bruger 1: " + bruger1index);
        System.out.println("index bruger 2: " + bruger2index);





    }
}
