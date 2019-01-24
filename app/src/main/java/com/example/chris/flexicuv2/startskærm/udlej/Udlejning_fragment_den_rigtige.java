package com.example.chris.flexicuv2.startskærm.udlej;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.medarbejdere.Medarbejder_recyclerView_adapter;

/**
 * @Author Christian
 */

public class Udlejning_fragment_den_rigtige extends Fragment implements View.OnClickListener {

    public Udlejning_fragment_den_rigtige() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    Medarbejder_recyclerView_adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.udlejning_fragment_den_rigtige, container, false);

        recyclerView =  v.findViewById(R.id.udlejning_recyclerview);
        recyclerView.setOnClickListener(this);


        fyldRecyclerView(v);
        return v;

    }

    @Override
    public void onClick(View v) {

    }

    private void fyldRecyclerView(View v){
        RecyclerView recyclerView = v.findViewById(R.id.udlejning_recyclerview);
        adapter = new Medarbejder_recyclerView_adapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
