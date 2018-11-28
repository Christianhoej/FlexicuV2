package com.example.chris.flexicuv2.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Test {

    private final DatabaseReference mDatabase;
    private Virksomhed virk = new Virksomhed();
    private Bruger bruger1 = new Bruger();
    private Bruger bruger2 = new Bruger();
    private Bruger bruger3 = new Bruger();
    private Medarbejder med1 = new Medarbejder();
    private Medarbejder med2 = new Medarbejder();
    private Medarbejder med3 = new Medarbejder();
    private Medarbejder med4 = new Medarbejder();
    private Medarbejder med5 = new Medarbejder();
    private Medarbejder med6 = new Medarbejder();
    private Medarbejder med7 = new Medarbejder();
    private Medarbejder med8 = new Medarbejder();



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
        bruger3.setNavn("Christian");
        bruger3.setEmail("christian@christian.com");
        bruger3.setTitel("CEO");
        bruger3.setTlfnr("34592034");
        bruger3.setAdmin(true);
        med1.setNavn("Oliver");
        med1.setLoen(100);
        med1.setPeriode("1/12-1/1");
        med1.setArbejdsomraade("Smed");
        med2.setNavn("Adam");
        med2.setLoen(115);
        med2.setPeriode("10/12-30/12");
        med2.setArbejdsomraade("Tømrer");
        med3.setNavn("Ahad");
        med3.setLoen(100);
        med3.setPeriode("1/12-1/1");
        med3.setArbejdsomraade("Smed");
        med4.setNavn("Christian");
        med4.setLoen(100);
        med4.setPeriode("1/12-1/1");
        med4.setArbejdsomraade("Smed");
        med5.setNavn("Janus");
        med5.setLoen(100);
        med5.setPeriode("1/12-1/1");
        med5.setArbejdsomraade("Smed");
        med6.setNavn("Mohammed");
        med6.setLoen(100);
        med6.setPeriode("1/12-1/1");
        med6.setArbejdsomraade("Smed");
        med7.setNavn("Sven-Erik");
        med7.setLoen(100);
        med7.setPeriode("1/12-1/1");
        med7.setArbejdsomraade("Smed");
        med8.setNavn("kljdfsklgjsdfg");
        med8.setLoen(100);
        med8.setPeriode("1/12-1/1");
        med8.setArbejdsomraade("Smed");
        virk.addBruger(bruger1);
        virk.addBruger(bruger2);
        virk.addBruger(bruger3);
        virk.addMedarbejdere(med1);
        virk.addMedarbejdere(med2);
        virk.addMedarbejdere(med3);
        virk.addMedarbejdere(med4);
        virk.addMedarbejdere(med5);
        virk.addMedarbejdere(med6);
        virk.addMedarbejdere(med7);
        virk.addMedarbejdere(med8);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("virksomhed").child(virk.getVirksomhedCVR()).setValue(virk);


        /*DatabaseReference dbBruger = FirebaseDatabase.getInstance().getReference();

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
        System.out.println("index bruger 2: " + bruger2index);*/





    }

    public Virksomhed getVirk() {
        return virk;
    }
}
