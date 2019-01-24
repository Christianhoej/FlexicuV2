package com.example.chris.flexicuv2.startskærm.hjem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Udfyld_medarbejdere extends Fragment {

    TextView tv;

    public Udfyld_medarbejdere() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_udfyld_medarbejdere, container, false);

        tv = v.findViewById(R.id.uTV);
        return v;
    }

}
