package com.example.chris.flexicuv2.startskærm.lej;

import android.webkit.JavascriptInterface;

public class Afstand_koordinater {
    //https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    public double calculateDistanceInKilometer(double medarbejderLat, double medarbejderLng, double arbejdeLat, double arbejdeLng) {

        double latDistance = Math.toRadians(medarbejderLat - arbejdeLat);
        double lngDistance = Math.toRadians(medarbejderLng - arbejdeLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(medarbejderLat)) * Math.cos(Math.toRadians(arbejdeLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
    }


}
