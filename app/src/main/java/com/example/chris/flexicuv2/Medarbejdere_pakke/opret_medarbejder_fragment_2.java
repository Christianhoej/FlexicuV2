package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.Indlejninger.MultiSelectionSpinner;
import com.example.chris.flexicuv2.R;

import java.util.ArrayList;
import java.util.List;

public class opret_medarbejder_fragment_2 extends Fragment {

    public opret_medarbejder_fragment_2() {

    }

    EditText medarbejder_email;
    EditText medarbejder_tlf;
    MultiSelectionSpinner arbejdsområde_spinner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.opret_medarbejder_fragment_2, container, false);

        //Edit texts

        medarbejder_email = (EditText) v.findViewById(R.id.editText_email);
        medarbejder_tlf = (EditText) v.findViewById(R.id.editText_tlf);

        //Spinnere
        arbejdsområde_spinner=(MultiSelectionSpinner) v.findViewById(R.id.arbejdsområde);



        opretSpinner();
        return v;
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