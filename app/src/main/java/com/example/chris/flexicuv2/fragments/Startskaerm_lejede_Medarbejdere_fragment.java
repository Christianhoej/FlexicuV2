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

public class Startskaerm_lejede_Medarbejdere_fragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private Test test;

    public Startskaerm_lejede_Medarbejdere_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_hjem_fragment, container, false);
        recyclerView = v.findViewById(R.id.lej_medarbejder_recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        // Inflate the layout for this fragment

        RecyclerViewAdapter_Lej mAdapter = new RecyclerViewAdapter_Lej(getContext(), test.getVirk().getMedarbejdere());

        recyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test = new Test();
    }
}
