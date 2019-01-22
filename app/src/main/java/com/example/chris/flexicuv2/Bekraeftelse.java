package com.example.chris.flexicuv2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chris.flexicuv2.model.Singleton;


public class Bekraeftelse extends Fragment implements View.OnClickListener {

    TextView navn, periode;
    Button afslut;
    Singleton singleton;

    public Bekraeftelse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bekraeftelse_fragment, container, false);

        periode = (TextView) v.findViewById(R.id.bekræftelse_periode);
        navn = (TextView) v.findViewById(R.id.bekræftelse_navn);
        afslut = (Button) v.findViewById(R.id.bekræftelse_afslut_knap);
        afslut.setOnClickListener(this);
        singleton = Singleton.getInstance();

        navn.setText(singleton.midlertidigMedarbejder.getNavn());
        periode.setText(singleton.midlertidigForhandling.getStartDato().replace(" ", "") + " - " + singleton.midlertidigForhandling.getEndDato().replace(" ", ""));

        return v;
    }

    @Override
    public void onClick(View v) {
        getActivity().onBackPressed();
    }
}