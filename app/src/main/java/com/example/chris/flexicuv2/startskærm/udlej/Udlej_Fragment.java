package com.example.chris.flexicuv2.startskærm.udlej;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.medarbejdere.Medarbejder_recyclerView_adapter;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Udlej_Fragment extends Fragment implements View.OnClickListener{


    public Udlej_Fragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    Udlejning_recyclerView_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //TODO husk lige at ændre navnet her
        View v = inflater.inflate(R.layout.udlejning_fragment_den_rigtige, container, false);

        recyclerView =  v.findViewById(R.id.udlejning_recyclerview);
        recyclerView.setOnClickListener(this);


        fyldRecyclerView(v);



        return v;
    }

    private void fyldRecyclerView(View v){
        //Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.udlejning_recyclerview);
        adapter = new Udlejning_recyclerView_adapter(getContext()/*, navneTest, arbejdsområderTest*/);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View v) {

    }
}
