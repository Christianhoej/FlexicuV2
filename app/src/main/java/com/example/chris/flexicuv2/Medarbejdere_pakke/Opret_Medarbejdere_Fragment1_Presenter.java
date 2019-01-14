package com.example.chris.flexicuv2.Medarbejdere_pakke;

public class Opret_Medarbejdere_Fragment1_Presenter {

    private UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag;
    private final String ERRORNAVN="NAVNFEJL";
    private final String ERRORKØN="KØNFEJL";
    private final String ERRORFØDSELSÅR="FØDSELSÅRFEJL";


    public Opret_Medarbejdere_Fragment1_Presenter(UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag){
        this.updateOpretMedarbejderFrag = updateOpretMedarbejderFrag;
    }
    interface UpdateOpretMedarbejderFrag{
        void errorNavn(String errorMsg);
        void errorKøn(String errorMsg);
        void errorFødselsår(String errorMsg);
    }

    /**
     *
     * @param navn medarbejderens navn
     * @param køn "mand" , "kvinde" eller "" (tom) hvis intet er valgt
     * @param fødselsår
     * @return
     */
    boolean korrektUdfyldtInformationFrag1(String navn, String køn, Integer fødselsår){
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
            // TODO dbManager skal oprette medarbejderen
            return true;
        }
    }
}
