package com.example.chris.flexicuv2.startskærm.indbakke.forhandling;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Forhandling_recyclerview_adapter;
import com.example.chris.flexicuv2.startskærm.udlej.Udlejning_recyclerView_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forhandling extends Fragment implements View.OnClickListener {

Forhandling_recyclerview_adapter adapter;


    public Forhandling() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.forhandling_fragment, container, false);


        //fyldRecyclerView(v);
    return v;
    }
/*
    private void fyldRecyclerView(View v){
        Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.forhandlinger_recyclerview);
        adapter = new Forhandling_recyclerview_adapter(getContext()/*, navneTest, arbejdsområderTest*/    //);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }

    @Override
    public void onClick(View v) {

    }


}
