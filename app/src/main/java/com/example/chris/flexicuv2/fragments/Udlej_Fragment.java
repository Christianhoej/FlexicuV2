package com.example.chris.flexicuv2.fragments;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Udlej_Fragment extends Fragment implements View.OnClickListener{


    public Udlej_Fragment() {
        // Required empty public constructor
    }

    private Button testKnap;
    private EditText adresse;
    private TextView denne;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_udlej_, container, false);

        testKnap = v.findViewById(R.id.testKnap);
        adresse = v.findViewById(R.id.adresse_felt);
        denne = v.findViewById(R.id.denne);

        testKnap.setOnClickListener(this);




        return v;
    }

    public void geolocate(View v) throws IOException{
        String adresse2 = adresse.getText().toString();

        Geocoder gc = new Geocoder(getContext());
        List<Address> list = gc.getFromLocationName(adresse2,1);
        Address add = list.get(0);
        String lokation = add.getLocality();

        double lat = add.getLatitude();
        double lng = add.getLongitude();

        denne.setText("Latitude: " + lat + "\nLongitude: " + lng);

    }

    @Override
    public void onClick(View v) {
        try {
            geolocate(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
