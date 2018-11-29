package com.example.chris.flexicuv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.Indlejninger.Lej_filtrer;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.Startskaerm;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lej_fragment extends Fragment implements View.OnClickListener {


    public Lej_fragment() {
        // Required empty public constructor
    }

    Startskaerm startskaerm;
    Lej_filtrer filtrer_fragment;
    FrameLayout lej_frame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lej_fragment, container, false);

        startskaerm = new Startskaerm();
        filtrer_fragment = new Lej_filtrer();
        lej_frame = (FrameLayout) v.findViewById(R.id.lej_fragment_frame);

        Button filtrer = (Button) v.findViewById(R.id.filtrer_knap);
        filtrer.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        setFragment(filtrer_fragment);
    }
    public void setFragment(Fragment fragment) {
        lej_frame.removeAllViews();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.lej_fragment_frame, fragment);
        fragmentTransaction.commit();
    }
}
