package com.example.chris.flexicuv2.medarbejdere;

import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

public class Opret_Medarbejdere_Fragment1_Presenter {

    private UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag;
    private final String ERRORNAVN="NAVNFEJL";
    private final String ERRORKØN="KØNFEJL";
    private final String ERRORFØDSELSÅR="FØDSELSÅRFEJL";
    private Singleton singleton;

    public Opret_Medarbejdere_Fragment1_Presenter(UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag){
        this.updateOpretMedarbejderFrag = updateOpretMedarbejderFrag;
        singleton = Singleton.getInstance();
    }
    interface UpdateOpretMedarbejderFrag{
        void errorNavn(String errorMsg);
        void errorKøn(String errorMsg);
        void errorFødselsår(String errorMsg);
        void setNavn(String navn);
        void setKøn(String køn);
        void setFødselsår(String fødselsår);
    }

    /**
     *
     * @param navn medarbejderens navn
     * @param køn "mand" , "kvinde" eller "" (tom) hvis intet er valgt
     * @param fødselsår
     * @return
     */
    public boolean korrektUdfyldtInformationFrag1(String navn, String køn, Integer fødselsår){
        int errors=0;


        boolean navnOK = !navn.isEmpty();
        if(!navnOK){
            updateOpretMedarbejderFrag.errorNavn(ERRORNAVN);
            errors++;
        }
        //TODO Skal laves
        boolean kønOK = køn != "";
        if(!navnOK){
            updateOpretMedarbejderFrag.errorKøn(ERRORKØN);
            errors++;
        }

        //Enten på denne måde eller med Janus's tjek om der er 4 cifre
        boolean fødselsårOK = fødselsår!=-1;
        if(!fødselsårOK){
            updateOpretMedarbejderFrag.errorFødselsår(ERRORFØDSELSÅR);
            errors++;
        }

        if(errors>0)
            return false;
        else{
            if(singleton.midlertidigMedarbejder==null) {
                singleton.midlertidigMedarbejder = new Medarbejder();
            }
            singleton.midlertidigMedarbejder.setNavn(navn);
            singleton.midlertidigMedarbejder.setKøn(køn);
            singleton.midlertidigMedarbejder.setFødselsår(fødselsår);


            return true;
        }
    }

    public void udfyldFelter(){
        if(singleton.midlertidigMedarbejder!=null){
            updateOpretMedarbejderFrag.setFødselsår(Integer.toString(singleton.midlertidigMedarbejder.getFødselsår()));
            updateOpretMedarbejderFrag.setKøn(singleton.midlertidigMedarbejder.getKøn());
            updateOpretMedarbejderFrag.setNavn(singleton.midlertidigMedarbejder.getNavn());
        }
    }
}
