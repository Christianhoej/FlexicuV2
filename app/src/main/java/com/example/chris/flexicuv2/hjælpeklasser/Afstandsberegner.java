package com.example.chris.flexicuv2.hjælpeklasser;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.webkit.JavascriptInterface;

import java.io.IOException;
import java.util.List;

public class Afstandsberegner {
    //https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    public static double calculateDistanceInKilometer(double medarbejderLat, double medarbejderLng, double arbejdeLat, double arbejdeLng) {

        double latDistance = Math.toRadians(medarbejderLat - arbejdeLat);
        double lngDistance = Math.toRadians(medarbejderLng - arbejdeLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(medarbejderLat)) * Math.cos(Math.toRadians(arbejdeLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }

    /**
     * Metoden udregner længde og breddegrad for en given adresse
     * @param context
     * @param vejnavn
     * @param husnummer
     * @param postNr
     * @return  Returnerer En String bestående af længde og breder grad separeret af et " " (mellemrum)
     * @throws IOException
     */
    public static String geolocate(Context context, String vejnavn, String husnummer, String postNr, String bynavn) throws IOException {

        String fuldAdresse;
        if(bynavn == null)
        fuldAdresse = vejnavn + " " + husnummer + " " + postNr + ", DK";
        else
            fuldAdresse= vejnavn + " " + husnummer +" " +bynavn +" " + postNr + ", DK";


        Geocoder gc = new Geocoder(context);

        List<Address> list = gc.getFromLocationName(fuldAdresse,1);
        Address address = list.get(0);
        System.out.println(address.getPostalCode() + " " + address.getLocality() + " Dette fandet jeg");
        return Double.toString(address.getLatitude()) + " " + Double.toString(address.getLongitude());


    }


}
