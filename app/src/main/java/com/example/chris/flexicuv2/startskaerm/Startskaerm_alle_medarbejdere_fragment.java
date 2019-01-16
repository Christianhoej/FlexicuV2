package com.example.chris.flexicuv2.startskaerm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;


public class Startskaerm_alle_medarbejdere_fragment extends Fragment {


    private View v;
    private RecyclerView recyclerView;
    private Singleton singleton;

    public Startskaerm_alle_medarbejdere_fragment() {
        singleton = Singleton.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.startskaerm_recyclerview, container, false);
        recyclerView = v.findViewById(R.id.startskaerm_recyclerview);
        TextView title = v.findViewById(R.id.statusAfMedarbejdere);
        title.setText("Mine medarbejdere");


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        // Inflate the layout for this fragment

        Startskaerm_MineMedarbejdere_RecyclerviewAdapter mAdapter = new Startskaerm_MineMedarbejdere_RecyclerviewAdapter(getContext(), singleton.getMedarbejdere());

        recyclerView.setAdapter(mAdapter);

        return v;
    }
}
