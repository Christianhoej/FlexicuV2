package com.example.chris.flexicuv2.startskærm.lej;

import com.example.chris.flexicuv2.model.Aftale;

public class Filter implements Filtrering {

    private String[]professioner;
    private double latitude, longitude;
    private boolean egetVærktøj;
    private int minPris, maxPris;
    private Afstand_koordinater afstand = new Afstand_koordinater();
    private int maxMatchScore;
    private String startdato, slutdato;


    public Filter(double latitude, double longitude, String arbejdsområder, boolean egetVærktøj, int minPris, int maxPris, String startdato, String slutdato){
    this.latitude = latitude;
    this.longitude = longitude;
    this.professioner = arbejdsområder.split(",");
    this.egetVærktøj = egetVærktøj;
    this.minPris = minPris;
    this.maxPris = maxPris;
    this.startdato = startdato;
    this.slutdato = slutdato;
    maxMatchScore = 100 + 20*(arbejdsområder.length()-1); //Professionsscoren
    maxMatchScore += 100; //Afstandsscoren
        //Tanker om periode filtrering
        //afstand fra startdag trækker fra ->
        maxMatchScore += 30; //eget værktøj score
        maxMatchScore += 10; // højere end minPris score
        maxMatchScore+=30; // lavere end maxPris score


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
    public int tildelMatchScore(Aftale a) {
        int match = 0;

        //Tjekker arbejdsområder og hvor mange matches der er
        String arbOmråde = a.getMedarbejder().getArbejdsomraade();
        int proffessionsOmråderMatches = 0;
        boolean fortsætFiltrer = false;
        for(String prof : professioner){
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
        double afstandFugleflugt = afstand.calculateDistanceInKilometer(Double.parseDouble(a.getMedarbejder().getLatitude()), Double.parseDouble(a.getMedarbejder().getLongitude()), latitude,longitude);
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

        //Hvis også har eget værktøj
        if(a.isEgetVærktøj() == egetVærktøj){
            match = match + 30;
        }
        //Hvis højere end minPris
        if (Integer.parseInt(a.getPris()) > minPris){
            match = match + 10;
        }
        //hvis mindre end minPris
        if (Integer.parseInt(a.getPris())<maxPris){
            match = match + 30;
        }



        return (int)(match/maxMatchScore*100);
    }
}
