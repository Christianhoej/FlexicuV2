package com.example.chris.flexicuv2.startskærm.lej;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.hjælpeklasser.MultiSelectionSpinner;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Christian
 * A simple {@link Fragment} subclass.
 */
public class Lej_filtrer_fragment extends Fragment implements View.OnClickListener {

    ArrayList<String> valgte_arbejdsområder = new ArrayList<>();
    //String[] arbejdsområder = {"Lagermand"};
    public Lej_filtrer_fragment() {
        // Required empty public constructor
    }

    private EditText postnr;
    private EditText by;
    private EditText vej;
    private EditText nummer;
    private RadioButton ja_værktøj;
    private RadioButton nej_værktøj;
    private EditText min_timepris;
    private EditText max_timepris;
    private MultiSelectionSpinner arbejdsområder_spinner;
    private Button anvend;
    private Button annuller;
    private Button nulstil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lej_filtrer_fragment, container, false);

        arbejdsområder_spinner = (MultiSelectionSpinner)v.findViewById(R.id.filtrer_arbejdsområder);
        postnr = (EditText) v.findViewById(R.id.edit_postnr);
        by = (EditText) v.findViewById(R.id.edit_by);
        vej = (EditText) v.findViewById(R.id.edit_vej);
        nummer = (EditText) v.findViewById(R.id.edit_nummer);
        ja_værktøj = (RadioButton) v.findViewById(R.id.ja_værktøj);
        nej_værktøj = (RadioButton) v.findViewById(R.id.nej_værktøj);
        min_timepris = (EditText)v.findViewById(R.id.min_timepris);
        max_timepris = (EditText) v.findViewById(R.id.max_timepris);
        anvend = v.findViewById(R.id.anvend_knap);
        anvend.setOnClickListener(this);
        annuller = v.findViewById(R.id.annuller_knap);
        annuller.setOnClickListener(this);
        nulstil = v.findViewById(R.id.nulstil_knap);
        nulstil.setOnClickListener(this);

        opretSpinner();
        return v;
    }

    public void opretSpinner(){
        List<String> arbejdsområde_listen = new ArrayList<String>();
        arbejdsområde_listen.add("Vælg arbejdsområde");
        arbejdsområde_listen.add("Håndværker");
        arbejdsområde_listen.add("Smed");
        arbejdsområde_listen.add("Lagermand");
        arbejdsområde_listen.add("Elektriker");
        arbejdsområder_spinner.setItems(arbejdsområde_listen);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anvend_knap:
                //TODO Hvad skal knapperne gøre
                break;
            case R.id.annuller_knap:
                break;
            case R.id.nulstil_knap:
                break;
        }

    }
}
