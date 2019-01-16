package com.example.chris.flexicuv2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.flexicuv2.R;

public class Hjem_fragment extends Fragment {

    public Hjem_fragment() {
        // Required empty public constructor
    }
    Startskaerm_Udlejede_medarbejder_fragment fragmentUdlejedeMed;
    Startskaerm_lejede_Medarbejdere_fragment fragmentLejedeMed;
    Startskaerm_alle_medarbejdere_fragment fragmentAlleMed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hjem_fragment, container, false);

        fragmentUdlejedeMed = new Startskaerm_Udlejede_medarbejder_fragment();
        fragmentLejedeMed = new Startskaerm_lejede_Medarbejdere_fragment();
        fragmentAlleMed = new Startskaerm_alle_medarbejdere_fragment();


        opretHjemFragmenter();


        return v;
    }

    public void opretHjemFragmenter(){
        fragmentUdlejedeMed = new Startskaerm_Udlejede_medarbejder_fragment();
        fragmentLejedeMed = new Startskaerm_lejede_Medarbejdere_fragment();
        fragmentAlleMed = new Startskaerm_alle_medarbejdere_fragment();
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTrans_hjem = fragMan.beginTransaction();

        fragTrans_hjem.add(R.id.startskaermUdlejFrame, fragmentUdlejedeMed);
        fragTrans_hjem.add(R.id.startskaermLejFrame, fragmentLejedeMed);
        fragTrans_hjem.add(R.id.startskaermAlleFrame, fragmentAlleMed);
        fragTrans_hjem.commit();
    }
}
