package com.example.chris.flexicuv2.startskærm.indbakke.aftaler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling;

/**
 * A simple {@link Fragment} subclass.
 */
public class Aftaler_forhandlinger_fragment extends Fragment implements View.OnClickListener {
    public Aftaler_forhandlinger_fragment() {}

    private RecyclerView recyclerView;
    Forhandling_recyclerview_adapter adapter;

    Button åben_forhandling;

    Forhandling forhandlingFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.aftaler_forhandlinger_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.forhandlinger_recyclerview);
        recyclerView.setOnClickListener(this);

        åben_forhandling = v.findViewById(R.id.vis_forhandling);
        åben_forhandling.setOnClickListener(this);

        forhandlingFragment = new Forhandling();

        fyldRecyclerView(v);
        return v;
    }

    @Override
    public void onClick(View v) {
        setFragment(forhandlingFragment);
    }

    private void fyldRecyclerView(View v){
        //Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.forhandlinger_recyclerview);
        adapter = new Forhandling_recyclerview_adapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setFragment(Fragment fragment) {
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }
}
