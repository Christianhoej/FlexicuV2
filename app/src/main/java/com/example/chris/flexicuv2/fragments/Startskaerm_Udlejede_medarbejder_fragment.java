package com.example.chris.flexicuv2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Test;


/**
 * A simple {@link Fragment} subclass.
 */
public class Startskaerm_Udlejede_medarbejder_fragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private Test test;

    public Startskaerm_Udlejede_medarbejder_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_hjem_fragment, container, false);
        recyclerView = v.findViewById(R.id.lej_medarbejder_recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        // Inflate the layout for this fragment

        RecyclerViewAdapter_Udlej mAdapter_udlej = new RecyclerViewAdapter_Udlej(getContext(), test.getVirk().getMedarbejdere());

        recyclerView.setAdapter(mAdapter_udlej);

        RecyclerViewAdapter_Lej mAdapter_lej = new RecyclerViewAdapter_Lej(getContext(), test.getVirk().getMedarbejdere());

        recyclerView.setAdapter(mAdapter_lej);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test = new Test();
    }
}