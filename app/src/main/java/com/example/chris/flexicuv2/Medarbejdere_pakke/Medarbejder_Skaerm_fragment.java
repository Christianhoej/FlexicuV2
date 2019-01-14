package com.example.chris.flexicuv2.Medarbejdere_pakke;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Medarbejder_Skaerm_fragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Medarbejdere_skaerm";

    private Button opretMedarbejdereKnap;

    //private FrameLayout medarbejdereFrame;
    private opret_medarbejder_fragment_1 opretMedarbejderFragment1;
    private RecyclerView recyclerView;
    medarbejder_recyclerView_adapter adapter;


    //Til en test
    private ArrayList<String > navneTest = new ArrayList<>();
    private ArrayList<String > arbejdsområderTest = new ArrayList<>();
    public Medarbejder_Skaerm_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.medarbejder__skaerm_fragment, container, false);
        Log.d(TAG, "onCreate: started");




        recyclerView =  v.findViewById(R.id.medarbejder_recyclerview);
        recyclerView.setOnClickListener(this);

        //Til test
        navneTest.add("Carl");
        navneTest.add("Mig");
        arbejdsområderTest.add("Smed");
        arbejdsområderTest.add("Håndværker");
        //Til test færdigt

        opretMedarbejdereKnap = v.findViewById(R.id.opret_medarbejder_knap);
        opretMedarbejdereKnap.setOnClickListener(this);

        //Test af recyclerview
        opretMedarbejderFragment1 = new opret_medarbejder_fragment_1();
        //medarbejdereFrame = v.findViewById(R.id.medarbejdere_frame);
        fyldRecyclerView(v);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opret_medarbejder_knap:
                //TODO test af at opdatere recyclerviewet med "nye medarbejdere" Bør sættes på sidste fragment Eller viewt bør opdateres med et
                 /*String s1 ="Janus rules";
                String s2 = "ALT";
                adapter.medarbejderTilføjet(s1,s2);*/
                setFragment(opretMedarbejderFragment1);
                break;
        }
    }
    public void setFragment(android.support.v4.app.Fragment fragment) {
        //medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    /**
     * Metode til at "fylde" recyclerViewet, med den dta der lægges i Test Arraylisterne
     * Denne data skal ændres fra testen til når databasen er opsat.
     */
    private void fyldRecyclerView(View v){
        Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.medarbejder_recyclerview);
        adapter = new medarbejder_recyclerView_adapter(getContext()/*, navneTest, arbejdsområderTest*/);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
