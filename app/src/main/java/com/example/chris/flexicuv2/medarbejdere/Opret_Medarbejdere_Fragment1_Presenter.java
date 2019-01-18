
package com.example.chris.flexicuv2.medarbejdere;


import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

public class Opret_Medarbejdere_Fragment1_Presenter {

    private UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag;
    private final String ERRORNAVN="Du skal indtaste et navn";
    private final String ERRORKØN="Du skal vælge et køn";
    private final String ERRORFØDSELSÅR="Du skal vælge et fødselsår";
    private final String ERRORVEJNAVN="Du skal indtaste et vejnavn";
    private final String ERRORNUMMER="Du skal indtaste et husnummer";
    private final String ERRORPOSTNR="Du skal indtaste et postnummer";
    private final String ERRORPOSTNR1 = "Postnummeret skal være 4 tal";
    private final String ERRORNUMMER1 = "Husnummeret skal være mellem 1 og 4 tal";
    private Singleton singleton;

    public Opret_Medarbejdere_Fragment1_Presenter(UpdateOpretMedarbejderFrag updateOpretMedarbejderFrag){
        this.updateOpretMedarbejderFrag = updateOpretMedarbejderFrag;
        singleton = Singleton.getInstance();
    }
    interface UpdateOpretMedarbejderFrag{
        void errorNavn(String errorMsg);
        void errorKøn(String errorMsg);
        void errorFødselsår(String errorMsg);
        void errorVejnavn(String errorMsg);
        void errorNummer(String errorMsg);
        void errorPostnr(String errorMsg);
        void setNavn(String navn);
        void setKøn(String køn);
        void setFødselsår(String fødselsår);
        void setVejnavn (String vejnavn);
        void setNummer (String nummer);
        void setPostnr (String postnr);
        void setLatitude (String latitude);
        void setLongitude (String longitude);
    }

    /**
     *
     * @param navn medarbejderens navn
     * @param køn "mand" , "kvinde" eller "" (tom) hvis intet er valgt
     * @param fødselsår
     * @return
     */
    public boolean korrektUdfyldtInformationFrag1(String navn, String køn, Integer fødselsår, String vejnavn, String nummer, String postnr /*, String latitude, String longitude*/){
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

        boolean vejnavnOK = !vejnavn.isEmpty();
        if(!vejnavnOK){
            updateOpretMedarbejderFrag.errorVejnavn(ERRORVEJNAVN);
            errors++;
        }

        if(nummer.isEmpty()){
            updateOpretMedarbejderFrag.errorNummer(ERRORNUMMER);
            errors++;
        }
        else if(nummer.length()<1 || nummer.length()>4){
            updateOpretMedarbejderFrag.errorNummer(ERRORNUMMER1);
            errors++;
        }



        if(postnr.isEmpty()) {
            updateOpretMedarbejderFrag.errorPostnr(ERRORPOSTNR);
            errors++;
        }
        else if (!checkStringOnlyNumbersAndLength(postnr, 4)){
            updateOpretMedarbejderFrag.errorPostnr(ERRORPOSTNR1);
            errors++;
        }


        //TODO Tjek om vejnavn, nummer, og postnr er ok.
        // TODO behøv det at tjekke for at nummer er ok, når inputtype er sat til number?

        if(errors>0)
            return false;
        else{
            if(singleton.midlertidigMedarbejder==null) {
                singleton.midlertidigMedarbejder = new Medarbejder();
            }
            singleton.midlertidigMedarbejder.setNavn(navn);
            singleton.midlertidigMedarbejder.setKøn(køn);
            singleton.midlertidigMedarbejder.setFødselsår(fødselsår);
            singleton.midlertidigMedarbejder.setVejnavn(vejnavn);
            singleton.midlertidigMedarbejder.setHusnummer(nummer);
            singleton.midlertidigMedarbejder.setPostnr(postnr);
            //singleton.midlertidigMedarbejder.setLatitude(latitude);
            //singleton.midlertidigMedarbejder.setLongitude(longitude);

            return true;
        }
    }

    private boolean checkStringOnlyNumbersAndLength(String cvr, int stringlength) {
        if(cvr.length() == stringlength) {
            int ammNumbers = 0;
            for (int i = 0; i < stringlength; i++) {
                if (cvr.charAt(i) <= '9' && cvr.charAt(i) >= '0') {
                    ammNumbers++;
                }
            }
            if(ammNumbers == stringlength)
                return true;
        }
        return false;
    }

    public void udfyldFelter(){
        if(singleton.midlertidigMedarbejder!=null){
            updateOpretMedarbejderFrag.setFødselsår(Integer.toString(singleton.midlertidigMedarbejder.getFødselsår()));
            updateOpretMedarbejderFrag.setKøn(singleton.midlertidigMedarbejder.getKøn());
            updateOpretMedarbejderFrag.setNavn(singleton.midlertidigMedarbejder.getNavn());
            updateOpretMedarbejderFrag.setVejnavn(singleton.midlertidigMedarbejder.getVejnavn());
            updateOpretMedarbejderFrag.setNummer(singleton.midlertidigMedarbejder.getHusnummer());
            updateOpretMedarbejderFrag.setPostnr(singleton.midlertidigMedarbejder.getPostnr());
        }
    }
}
