package com.example.chris.flexicuv2.startskærm.hjem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;

public class Hjem_fragment extends Fragment {

    Singleton singleton;

    public Hjem_fragment() {
        // Required empty public constructor
    }
    Startskaerm_Udlejede_medarbejder_fragment fragmentUdlejedeMed;
    Startskaerm_lejede_Medarbejdere_fragment fragmentLejedeMed;
    Startskaerm_alle_medarbejdere_fragment fragmentAlleMed;
    //RelativeLayout udfyld_medarbejder;
    Udfyld_medarbejdere udfyld_medarbejdere;
    Udfyld_andet udfyld_andet;
    Udfyld_udlej udfyld_udlej;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.hjem_fragment, container, false);
        singleton = Singleton.getInstance();
        opretHjemFragmenter();

        return v;
    }

    public void opretHjemFragmenter(){
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTrans_hjem = fragMan.beginTransaction();

        if(singleton.getMineUdlejIndgåedeAftaler().size()==0){
            udfyld_udlej = new Udfyld_udlej();
            fragTrans_hjem.add(R.id.startskaermUdlejFrame, udfyld_udlej);
        }
        else {
            fragmentUdlejedeMed = new Startskaerm_Udlejede_medarbejder_fragment();
            fragTrans_hjem.add(R.id.startskaermUdlejFrame, fragmentUdlejedeMed);
        }
        if(singleton.getMineLejIndgåedeAftaler().size()==0){
            udfyld_andet = new Udfyld_andet();
            fragTrans_hjem.add(R.id.startskaermLejFrame, udfyld_andet);
        }
        else{
            fragmentLejedeMed = new Startskaerm_lejede_Medarbejdere_fragment();
            fragTrans_hjem.add(R.id.startskaermLejFrame, fragmentLejedeMed);
        }
        if(singleton.getMedarbejdere().size()==0){
            udfyld_medarbejdere = new Udfyld_medarbejdere();
            fragTrans_hjem.add(R.id.startskaermAlleFrame, udfyld_medarbejdere);
        }
        else{
            fragmentAlleMed = new Startskaerm_alle_medarbejdere_fragment();
            fragTrans_hjem.add(R.id.startskaermAlleFrame, fragmentAlleMed);
        }


        fragTrans_hjem.commit();



    }
}
