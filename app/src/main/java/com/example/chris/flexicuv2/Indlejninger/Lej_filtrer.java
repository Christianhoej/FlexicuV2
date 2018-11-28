package com.example.chris.flexicuv2.Indlejninger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lej_filtrer, container, false);

        /*RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        com.example.chris.flexicuv2.Indlejninger.ListAdapter listAdapter = new com.example.chris.flexicuv2.Indlejninger.ListAdapter();
        recyclerView.setAdapter(listAdapter);*/

        String[] items = {"Håndværker", "Smed", "Lagermand"};

        MultiSelectionSpinner spinner=(MultiSelectionSpinner)v.findViewById(R.id.input1);

        List<String> list = new ArrayList<String>();
        list.add("Håndværker");
        list.add("Smed");
        list.add("Lagermand");
        spinner.setItems(list);

        return v;
    }






}
