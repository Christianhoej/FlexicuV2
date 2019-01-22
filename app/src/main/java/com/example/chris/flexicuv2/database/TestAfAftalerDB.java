package com.example.chris.flexicuv2.database;

import com.example.chris.flexicuv2.model.Singleton;

public class TestAfAftalerDB {


    private Singleton singleton;

    public TestAfAftalerDB(){
        singleton = Singleton.getInstance();
    }


    /*public Aftale getUdlej(){
        singleton.midlertidigMedarbejder = new Medarbejder();
      //  singleton.midlertidigMedarbejder.setVirksomhedsID(singleton.getBruger().getBrugerID());
        singleton.midlertidigMedarbejder.setArbejdsomraade("Smed");
        singleton.midlertidigMedarbejder.setTlfnr("93911123");
        singleton.midlertidigMedarbejder.setEmail("gunn@gunn.dk");
        singleton.midlertidigMedarbejder.setFødselsår(1992);
        singleton.midlertidigMedarbejder.setKøn("kvinde");
        singleton.midlertidigMedarbejder.setNavn("Gunn");
        singleton.midlertidigMedarbejder.setVejnavn("Dalslandsgade 8G");
        singleton.midlertidigMedarbejder.setHusnummer("8G");
        singleton.midlertidigMedarbejder.setPostnr("2300");


        Aftale udlej = new Aftale();

        udlej.setMedarbejder(singleton.midlertidigMedarbejder);
        udlej.setUdlejer(singleton.getBruger());
        udlej.setEgetVærktøj(false);
        udlej.setEndDato("19/06-2019");
        udlej.setPris("200");
        udlej.setStartDato("15/3-2019");
        udlej.setKommentar("Har egen bil");
        udlej.setStatus(1);
        return udlej;
    }*/

}
