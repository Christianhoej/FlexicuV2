package com.example.chris.flexicuv2.startskærm.indbakke.aftaler;


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
public class Aftaler_nuvaerende_fragment extends Fragment implements View.OnClickListener {
    public Aftaler_nuvaerende_fragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    Aftaler_recyclerview_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.aftaler_nuvaerende_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.nuværende_recyclerview);
        recyclerView.setOnClickListener(this);

        fyldRecyclerView(v);


        return v;
    }

    @Override
    public void onClick(View v) {

    }
    private void fyldRecyclerView(View v){
        //Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        recyclerView = v.findViewById(R.id.nuværende_recyclerview);
        adapter = new Aftaler_recyclerview_adapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
