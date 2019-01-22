package com.example.chris.flexicuv2.startskærm.lej;

import com.example.chris.flexicuv2.hjælpeklasser.Afstandsberegner;
import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;
import com.example.chris.flexicuv2.model.Forhandling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Filter implements Filtrering {

    private String[]arbejdsområder;

    private String latitude;
    private String longitude;
    private boolean egetVærktøj;
    private String minPris, maxPris;
    private Afstandsberegner afstand = new Afstandsberegner();
    private int maxMatchScore;
    private String startdato;
    private String slutdato;
    private String postNr, by, nummer, vej;


    public Filter(){
        latitude = "0";
        longitude = "0";
        egetVærktøj = false;
        minPris = "";
        maxPris = "";
        arbejdsområder = null;
        //System.out.println(new SimpleDateFormat("dd/MMM/yyyy").format(cal));
        //hvis ingen startdato er angivet er det den nuværende
        startdato = " dd / mm / yyyy ";
        slutdato = " dd / mm / yyyy ";
        postNr = "";
        by = "";
        nummer = "";
        vej = "";


    }

    public String getPostNr() {
        return postNr;
    }

    public void setPostNr(String postNr) {
        this.postNr = postNr;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getVej() {
        return vej;
    }

    public void setVej(String vej) {
        this.vej = vej;
    }


    public String[] getArbejdsområder() {
        return arbejdsområder;
    }

    public void setArbejdsområder(String arbejdsområder) {
        this.arbejdsområder = arbejdsområder.split(",");
    }

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

    public boolean isEgetVærktøj() {
        return egetVærktøj;
    }

    public void setEgetVærktøj(boolean egetVærktøj) {
        this.egetVærktøj = egetVærktøj;
    }

    public String getMinPris() {
        return minPris;
    }

    public void setMinPris(String minPris) {
        this.minPris = minPris;
    }

    public String getMaxPris() {
        return maxPris;
    }

    public void setMaxPris(String maxPris) {
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
        if(arbejdsområder!=null) { //Hvis der er valgt arbejdsområde, så fortsæt
            //Tjekker arbejdsområder og hvor mange matches der er
            String arbOmråde = a.getMedarbejder().getArbejdsomraade();
            int proffessionsOmråderMatches = 0;
            boolean fortsætFiltrer = false;
            for (String prof : arbejdsområder) {
                if (arbOmråde.contains(prof)) {
                    fortsætFiltrer = true;
                    proffessionsOmråderMatches++;
                }
            }
            //hvis ikke der er match på arbejdsområder, så er match = 0 %
            if (!fortsætFiltrer)
                return 0;
            //tildeler score for matches i arbejdsområde
            //100 point hvis man matcher bare én profession og yderligere 20 for hver ekstra profession derudover
            match = 100 + (proffessionsOmråderMatches - 1) * 20;
        }else
            match += 100;



        //check om der er angivet arbejdssted.
        if(!latitude.equals("0")) {
            //Hvis afstanden er inden for "Kørselsafstanden angivet for medarbejderen"
            // Score på 100 gives hvis personens "villig kørsel" dækker lokationen
            //25% forventes det at medarbejderen yderligere "kan være" villig til at køre.
            double afstandFugleflugt = afstand.calculateDistanceInKilometer(Double.parseDouble(a.getMedarbejder().getLatitude()), Double.parseDouble(a.getMedarbejder().getLongitude()), Double.parseDouble(latitude), Double.parseDouble(longitude));
            if (a.getMedarbejder().getVilligKørsel() >= afstandFugleflugt) {
                match = match + 100;
            } else {
                //Scoren trækkes ned med 10 for hver gang der lægges 5% forøgelse medarbejderen skal køre til
                int muligPointKørsel = 100;
                int i = 5;
                while (i >= 25) {
                    double villigAfstand = a.getMedarbejder().getVilligKørsel();
                    villigAfstand = villigAfstand * 1.01 * i; //Lægger 5% af kørsel til hver gang
                    if (afstandFugleflugt > villigAfstand) {
                        muligPointKørsel = muligPointKørsel - 2 * i;
                        break;
                    } else i = i + 5;
                }
                match = match + muligPointKørsel;
            }
        }else //hvis ikke der er angivvet arbejdssted +=100
            match +=100;

        //Hvis også har eget værktøj eller søgningen er ligeglad med om der er værktøj med
        if(a.isEgetVærktøj()==true || egetVærktøj==false){
            match = match + 30;
        }

        int minPris = Integer.parseInt(this.minPris);
        //Hvis højere end minPris
        if (Integer.parseInt(a.getPris()) > minPris){
            match = match + 20;
        }
        int maxPris = Integer.parseInt(this.maxPris);
        //hvis mindre end eller = maxPris
        if (Integer.parseInt(a.getPris())<=maxPris || maxPris==0){
            match = match + 100;
        }else{ // procentvise afvigelse fra maxPrisen * de 100
            match = match +(int)(maxPris/a.getTimepris())*100;
        }


        //Datoscore gives på baggrund af % af perioden der dækkes. angives der hverken start eller slutdato
        int datoScore = 0;
        //if (startdato.equals("0") && slutdato.equals("0")) //  -> får alt en score på 100
        //datoScore=100;
        //if slutdato != 0 && startdato = "0" -> check om start

        //Hvis der er angivet både start og slutdato
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
