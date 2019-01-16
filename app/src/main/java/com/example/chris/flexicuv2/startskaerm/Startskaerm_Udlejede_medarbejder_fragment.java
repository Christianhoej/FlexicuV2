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



/**
 * A simple {@link Fragment} subclass.
 */
public class Startskaerm_Udlejede_medarbejder_fragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private Singleton singleton;

    public Startskaerm_Udlejede_medarbejder_fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.startskaerm_recyclerview, container, false);
        singleton = Singleton.getInstance();
        recyclerView = v.findViewById(R.id.startskaerm_recyclerview);
        TextView title = v.findViewById(R.id.statusAfMedarbejdere);
        title.setText("Udlejede medarbejdere");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        // Inflate the layout for this fragment

        Startskaerm_UdlejedeMedarbejdere_RecyclerviewAdapter mAdapter_udlej = new Startskaerm_UdlejedeMedarbejdere_RecyclerviewAdapter(getContext(), singleton.getMedarbejdere());

        recyclerView.setAdapter(mAdapter_udlej);


        //RecyclerViewAdapter_Lej mAdapter_lej = new RecyclerViewAdapter_Lej(getContext(), singleton.getMedarbejdere());

        //recyclerView.setAdapter(mAdapter_lej);

        return v;
    }
}
