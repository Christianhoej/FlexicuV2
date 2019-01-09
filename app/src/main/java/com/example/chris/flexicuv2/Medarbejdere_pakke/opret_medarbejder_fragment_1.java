package com.example.chris.flexicuv2.Medarbejdere_pakke;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.chris.flexicuv2.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class opret_medarbejder_fragment_1 extends Fragment implements View.OnClickListener {
    public opret_medarbejder_fragment_1() {
    }

    Spinner fødselsår_spinner;
    EditText medarbejder_navn;

    FrameLayout medarbejdereFrame;
    private opret_medarbejder_fragment_2 opretMedarbejderFragment2;

    Button næste_medarbejdere;
    Button tilbage1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.opret_medarbejder_fragment_1, container, false);
        //Fødselsår spinner
        fødselsår_spinner = v.findViewById(R.id.fødselsår);
        ArrayList<String> år= new ArrayList<>();
        år.add("Vælg fødselsår");
        for (Integer i=2004; i>1940; i--) {
            år.add(i.toString());
        }
        ArrayAdapter<String> adapter_år = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, år);
        fødselsår_spinner.setAdapter(adapter_år);

        //Medarbejderens navn
        medarbejder_navn = (EditText) v.findViewById(R.id.editText_navn);

        //Bemærk denne er anderledes fra sidste side
        medarbejdereFrame = (FrameLayout) v.findViewById(R.id.opret_medarbejder_frame_1);

        opretMedarbejderFragment2 = new opret_medarbejder_fragment_2();
        næste_medarbejdere = (Button) v.findViewById(R.id.næste_medarbejdere);
        næste_medarbejdere.setOnClickListener(this);
        tilbage1 = (Button) v.findViewById(R.id.tilbage_medarbejdere);
        tilbage1.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.næste_medarbejdere:
                setFragment(opretMedarbejderFragment2);
                break;
            case R.id.tilbage_medarbejdere:
                getActivity().onBackPressed();
                break;
        }
    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }
}
