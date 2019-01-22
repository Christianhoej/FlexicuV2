package com.example.chris.flexicuv2.startskærm.lej;

import com.example.chris.flexicuv2.hjælpeklasser.Afstandsberegner;
import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;
import com.example.chris.flexicuv2.model.Forhandling;

public class Filter implements Filtrering {

    private String[]arbejdsområder;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    private String latitude, longitude;
    private boolean egetVærktøj;
    private int minPris, maxPris;
    private Afstandsberegner afstand = new Afstandsberegner();
    private int maxMatchScore;
    private String startdato;
    private String slutdato;

    public String[] getArbejdsområder() {
        return arbejdsområder;
    }

    public void setArbejdsområder(String arbejdsområder) {
        this.arbejdsområder = arbejdsområder.split(",");
    }

    public boolean isEgetVærktøj() {
        return egetVærktøj;
    }

    public void setEgetVærktøj(boolean egetVærktøj) {
        this.egetVærktøj = egetVærktøj;
    }

    public int getMinPris() {
        return minPris;
    }

    public void setMinPris(int minPris) {
        this.minPris = minPris;
    }

    public int getMaxPris() {
        return maxPris;
    }

    public void setMaxPris(int maxPris) {
        this.maxPris = maxPris;
    }




    public String getStartdato() {
        return startdato;
    }

    public void setStartdato(String startdato) {
        this.startdato = startdato;
    }

    public String getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(String slutdato) {
        this.slutdato = slutdato;
    }



    public Filter(){

    }
    private int getMaxMatchScore(){
        return maxMatchScore;
    }

    /**
     * Metoden anvendes til at give
     * @param a Aftalen der skal vurderes op i mod søge kriterierne
     * @return %match i form af double.
     */
    @Override
    public int tildelMatchScore(Forhandling a) {

        //Sætter Max match point
        maxMatchScore = 100 + (20*arbejdsområder.length-1); //arbejdsområdeScoren
        maxMatchScore += 100; //Afstandsscoren
        maxMatchScore += 30; //eget værktøj score
        maxMatchScore += 20; // højere end minPris score
        maxMatchScore+=100; // lavere end maxPris score
        maxMatchScore+= 100; // datoscore % af peridoen dækket




        int match = 0;
        //Tjekker arbejdsområder og hvor mange matches der er
        String arbOmråde = a.getMedarbejder().getArbejdsomraade();
        int proffessionsOmråderMatches = 0;
        boolean fortsætFiltrer = false;
        for(String prof : arbejdsområder){
            if (arbOmråde.contains(prof)) {
                fortsætFiltrer = true;
                proffessionsOmråderMatches++;
            }
        }
        //hvis ikke der er match på arbejdsområder, så er match = 0 %
        if(!fortsætFiltrer)
            return 0;
        //tildeler score for matches i arbejdsområde
        //100 point hvis man matcher bare én profession og yderligere 20 for hver ekstra profession derudover
        match = 100 + (proffessionsOmråderMatches-1)*20;


        //Hvis afstanden er inden for "Kørselsafstanden angivet for medarbejderen"
        // Score på 100 gives hvis personens "villig kørsel" dækker lokationen
        //25% forventes det at medarbejderen yderligere "kan være" villig til at køre.
        //
        double afstandFugleflugt = afstand.calculateDistanceInKilometer(Double.parseDouble(a.getMedarbejder().getLatitude()), Double.parseDouble(a.getMedarbejder().getLongitude()), Double.parseDouble(latitude),Double.parseDouble(longitude));
        if(a.getMedarbejder().getVilligKørsel()>=afstandFugleflugt){
            match = match + 100;
        }
        else{
            //Scoren trækkes ned med 10 for hver gang der lægges 5% forøgelse medarbejderen skal køre til
            int muligPointKørsel = 100;
            int i = 5;
            while(i>= 25){
                double villigAfstand = a.getMedarbejder().getVilligKørsel();
                villigAfstand = villigAfstand*1.01*i; //Lægger 5% af kørsel til hver gang
                if (afstandFugleflugt>villigAfstand){
                    muligPointKørsel = muligPointKørsel -2*i;
                    break;
                }
                else i = i+5;
            }
            match = match + muligPointKørsel;
        }

        //Hvis også har eget værktøj eller søgningen er ligeglad med om der er værktøj med
        if(a.isEgetVærktøj()==true){
            match = match + 30;
        }


        //Hvis højere end minPris
        if (Integer.parseInt(a.getPris()) > minPris){
            match = match + 20;
        }
        //hvis mindre end eller = maxPris
        if (Integer.parseInt(a.getPris())<=maxPris){
            match = match + 100;
        }else{ // procentvise afvigelse fra maxPrisen * de 100
            match = match +(int)(maxPris/a.getTimepris())*100;
        }


        //Datoscore gives på baggrund af % af perioden der dækkes
        int datoScore = 0;
        int filtreringsArbejdsdage = Arbejdsdage_Kalender.findArbejdsdage(startdato, slutdato); //finder samlede antal arbejdsdage
        int forskelslutDatoer = Arbejdsdage_Kalender.findArbejdsdage(a.getEndDato(), slutdato); //returnerer 0 eller et negativt tal, så falder "aftalens afslutning" efter filtreringens slutdato
        if(forskelslutDatoer<0)
            forskelslutDatoer=0;
        int forskelstartDatoer = Arbejdsdage_Kalender.findArbejdsdage(startdato, a.getStartDato());// returnerer et negativt tal eller 0, så dækkes hele hele "starten af perioden"
        if(forskelstartDatoer <0)
            forskelstartDatoer = 0;
        int aftalens_arbejdsdage = Arbejdsdage_Kalender.findArbejdsdage(a.getStartDato(),a.getEndDato());

        if(forskelslutDatoer>=0 && forskelstartDatoer <=0)
        datoScore = 100;
        else{
            int dageDækket = filtreringsArbejdsdage - forskelslutDatoer - forskelstartDatoer;
            datoScore = (int)(dageDækket/filtreringsArbejdsdage*100);
        }
        match +=datoScore;





        return (int)(match/maxMatchScore*100);
    }
}
