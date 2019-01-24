package com.example.chris.flexicuv2.startskærm.hjem;

/**
 * @Author Christian
 */
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
public class Udfyld_udlej extends Fragment {

    private TextView tv2;
    public Udfyld_udlej() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_udfyld_udlej, container, false);
        tv2 = v.findViewById(R.id.tv2);
        return v;
    }

}
