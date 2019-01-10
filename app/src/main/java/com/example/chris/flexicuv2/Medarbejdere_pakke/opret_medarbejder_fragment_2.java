package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.chris.flexicuv2.Indlejninger.MultiSelectionSpinner;
import com.example.chris.flexicuv2.R;

import java.util.ArrayList;
import java.util.List;

public class opret_medarbejder_fragment_2 extends Fragment implements View.OnClickListener {

    public opret_medarbejder_fragment_2() {

    }

    EditText medarbejder_email;
    EditText medarbejder_tlf;
    MultiSelectionSpinner arbejdsområde_spinner;

    FrameLayout medarbejdereFrame;


    FrameLayout opret_medarbejder_1_Frame;
    private opret_medarbejder_fragment_1 opretMedarbejderFragment1;

    Button næste_medarbejdere2;
    Button tilbage2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.opret_medarbejder_fragment_2, container, false);

        //Edit texts
        medarbejder_email = (EditText) v.findViewById(R.id.editText_email);
        medarbejder_tlf = (EditText) v.findViewById(R.id.editText_tlf);

        //Spinnere
        arbejdsområde_spinner=(MultiSelectionSpinner) v.findViewById(R.id.arbejdsområde);

        medarbejdereFrame = (FrameLayout) v.findViewById(R.id.opret_medarbejder_frame_1);

        //Frame Layoutet for opret medarbejder 1
        opret_medarbejder_1_Frame = (FrameLayout) v.findViewById(R.id.opret_medarbejder_frame_1);
        //Fragmentet for opret medarbejder 1
        opretMedarbejderFragment1 = new opret_medarbejder_fragment_1();

        næste_medarbejdere2 = (Button) v.findViewById(R.id.næste_medarbejdere2);
        næste_medarbejdere2.setOnClickListener(this);
        tilbage2 = (Button) v.findViewById(R.id.tilbage_medarbejdere2);
        tilbage2.setOnClickListener(this);

        opretSpinner();
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.næste_medarbejdere2:
                //getActivity().onBackPressed();
                Toast.makeText(getContext(), "HURRA", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tilbage_medarbejdere2:
                getActivity().onBackPressed();
                break;
        }
    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        //medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    public void opretSpinner(){
        List<String> arbejdsområde_liste = new ArrayList<String>();
        arbejdsområde_liste.add("Vælg arbejdsområde");
        arbejdsområde_liste.add("Håndværker");
        arbejdsområde_liste.add("Smed");
        arbejdsområde_liste.add("Lagermand");
        arbejdsområde_liste.add("Elektriker");
        arbejdsområde_spinner.setItems(arbejdsområde_liste);
    }



}