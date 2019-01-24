
package com.example.chris.flexicuv2.medarbejdere;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chris.flexicuv2.R;

/**
 * A simple {@link Fragment} subclass.
 * @Author Christian
 */
public class Medarbejder_popup_fragment extends Fragment {


    public Medarbejder_popup_fragment() {
        // Required empty public constructor
    }

    Button tilbage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.medarbejder_popup_fragment, container, false);

        return v;
    }
}
