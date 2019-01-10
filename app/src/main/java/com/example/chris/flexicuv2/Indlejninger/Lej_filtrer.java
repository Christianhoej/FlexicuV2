package com.example.chris.flexicuv2.Indlejninger;


import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.example.chris.flexicuv2.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lej_filtrer extends Fragment {

    ArrayList<String> valgte_arbejdsområder = new ArrayList<>();
    //String[] arbejdsområder = {"Lagermand"};
    public Lej_filtrer() {
        // Required empty public constructor
    }

    EditText postnr;
    EditText by;
    EditText vej;
    EditText nummer;
    RadioButton ja_værktøj;
    RadioButton nej_værktøj;
    EditText min_timepris;
    EditText max_timepris;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lej_filtrer, container, false);

        postnr = (EditText) v.findViewById(R.id.edit_postnr);
        by = (EditText) v.findViewById(R.id.edit_by);
        vej = (EditText) v.findViewById(R.id.edit_vej);
        nummer = (EditText) v.findViewById(R.id.edit_nummer);
        ja_værktøj = (RadioButton) v.findViewById(R.id.ja_værktøj);
        nej_værktøj = (RadioButton) v.findViewById(R.id.nej_værktøj);
        min_timepris = (EditText)v.findViewById(R.id.min_timepris);
        max_timepris = (EditText) v.findViewById(R.id.max_timepris);

        MultiSelectionSpinner spinner=(MultiSelectionSpinner)v.findViewById(R.id.input1);

        List<String> list = new ArrayList<String>();
        list.add("Vælg arbejdsområde");
        list.add("Håndværker");
        list.add("Smed");
        list.add("Lagermand");
        list.add("Elektriker");
        spinner.setItems(list);

        return v;
    }









}
