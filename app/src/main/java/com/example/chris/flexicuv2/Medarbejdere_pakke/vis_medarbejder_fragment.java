package com.example.chris.flexicuv2.Medarbejdere_pakke;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.flexicuv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class vis_medarbejder_fragment extends Fragment {


    public vis_medarbejder_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.vis_medarbejder_popup_fragment, container, false);
    }

}
