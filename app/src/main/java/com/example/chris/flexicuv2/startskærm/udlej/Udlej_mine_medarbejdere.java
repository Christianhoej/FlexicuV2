package com.example.chris.flexicuv2.startskærm.udlej;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.flexicuv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Udlej_mine_medarbejdere extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    Udlejning_recyclerView_adapter adapter;

    public Udlej_mine_medarbejdere() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.udlej_mine_medarbejdere_fragment, container, false);

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
